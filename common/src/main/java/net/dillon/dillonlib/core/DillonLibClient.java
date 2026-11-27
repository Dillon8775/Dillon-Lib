package net.dillon.dillonlib.core;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.client.command.ClientCommandFeedback;
import net.dillon.dillonlib.client.command.ReloadConfigCommand;
import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.client.component.UpdatableSpriteButton;
import net.dillon.dillonlib.client.screen.DillonsModsScreen;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.widespread.DillonsSprites;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ScaledWidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.function.Function;

import static net.dillon.dillonlib.task.ClientTasks.getMinecraft;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;

/**
 * {@code Client} initialization entrypoint for DillonLib.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public class DillonLibClient {

    /**
     * Initializes the {@code client-side} of DillonLib.
     */
    public static void cInitialize() {
        DillonLibClient.clientInitializers().forEach(Runnable::run);

        int clientPlatforms = Platforms.forEachClientPlatform(clientModPlatform -> {
            if (!clientModPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                DillonLibMain.LOGGER.info("ClientModPlatform loaded with mod ID {}",
                        clientModPlatform.modId()
                );
            }
        });

        DillonLibMain.LOGGER.info("Loaded {} client platforms", clientPlatforms);
        DillonLibMain.LOGGER.info("(Client) DillonLib has loaded");
    }

    /**
     * @return all client-side initializer methods for {@code client-side only classes.}
     */
    private static List<Runnable> clientInitializers() {
        return List.of(
                ClientFactories::i_
        );
    }

    /**
     * Creates the {@code configure mods button}.
     */
    public static UpdatableSpriteButton configureModsButton(Screen parent) {
        boolean anyModHasUpdate = anyModHasUpdate();

        Component defaultTooltip = anyModHasUpdate
                ? Component.translatable("dillonlib.updates_available")
                : Component.translatable("dillonlib.mods");

        return new UpdatableSpriteButton(
                "Configure Mods",
                20,
                20,
                () -> anyModHasUpdate,
                new ScaledWidgetSprites(DillonsSprites.DILLONLIB_LOGO, 16, 16),
                DillonLibMain.MOD_INFO.hasUpdate()
                        ? Component.translatable("dillonlib.update_available_message", Component.translatable("dillonlib"))
                        : defaultTooltip,
                onPress -> openScreen(new DillonsModsScreen(parent))
        );
    }

    /**
     * @return if any mod has an update available.
     */
    private static boolean anyModHasUpdate() {
        if (DillonLibMain.MOD_INFO.hasUpdate()) {
            return true;
        }

        boolean[] bl = {false};
        Platforms.forEachClientPlatform(clientModPlatform -> {
            for (PlatformMenuButton p : clientModPlatform.menuButtons()) {
                if (p.button() == null) {
                    continue;
                }

                if (p.button().shouldRenderUpdateSprite().getAsBoolean()) {
                    bl[0] = true;
                    return;
                }
            }
        });

        return bl[0];
    }

    /**
     * @return the full bright setting component string.
     */
    public static Component toComponentString(Double gamma) {
        long brightness = Math.round(gamma * 100);
        return Component.translatable("options.gamma").append(": ").append(brightness == 0 ? Component.translatable("options.gamma.min") : brightness == 100 ? Component.translatable("options.gamma.max") : Component.literal(String.valueOf(brightness)));
    }

    /**
     * Updates full bright.
     */
    public static void onValueUpdate(Double brightness) {
        Minecraft.getInstance().options.gamma().set(brightness);
    }

    /**
     * Event handling for the client-side of DillonLib.
     */
    public static class Events {

        /**
         * Registers the {@link ReloadConfigCommand}.
         */
        public static <S> void registerReloadCommand(CommandDispatcher<S> dispatcher, Function<S, ClientCommandFeedback> feedback) {
            dispatcher.register(ReloadConfigCommand.i_(feedback));
        }

        /**
         * Registers all {@code client} commands.
         */
        public static <S> void registerAllClientCommands(CommandDispatcher<S> commandDispatcher, Function<S, ClientCommandFeedback> feedback, CommandBuildContext commandRegistryAccess) {
            Platforms.forEachClientPlatform(modPlatform -> modPlatform.registerClientCommands(commandDispatcher, feedback, commandRegistryAccess));
        }

        /**
         * Runs all client tick events for each platform.
         */
        public static void layClientTicking(LocalPlayer localPlayer) {
            Platforms.forEachClientPlatform(ClientModPlatform::clientRuntimeTick);

            if (localPlayer != null) {
                Platforms.forEachClientPlatform(clientModPlatform -> {
                    if (!getMinecraft().isPaused()) {
                        clientModPlatform.clientGameTick();
                    }
                });
            }

            DillonLibMain.Events.tickTasks(ClientTasks.getQueuedClientTasks());
        }
    }
}