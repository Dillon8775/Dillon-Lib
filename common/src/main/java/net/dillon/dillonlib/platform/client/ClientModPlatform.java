package net.dillon.dillonlib.platform.client;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.command.ClientCommandFeedback;
import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.platform.Loadable;
import net.dillon.dillonlib.platform.ModPlatform;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.core.WritableRegistry;

import java.util.List;
import java.util.function.Function;

/**
 * An instance of {@link Loadable}. Similar to {@link ModPlatform}, all methods used inside this class should be {@code client-side} only methods, meaning they should not interfere with the common code.
 * @since 1.0
 * @see Loadable
 * @see ModPlatform
 */
@Dill(env = DillEnv.CLIENT)
public abstract class ClientModPlatform implements Loadable {

    /**
     * @return the mod id for this client platform. Should return your mod id from your common mod class.
     */
    @Override
    public abstract String modId();

    /**
     * @return if a packet in your mod can be sent. It's helpful to check if a packet can be sent when joining a server that doesn't have your mod installed, which can inform the user of mod incompatibility. If you don't have a reason to check if a packet can be sent in your mod, you can just return false here.
     */
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return false;
    }

    /**
     * Add runtime tick events here. The player does not need to be in a world or server.
     */
    public void clientRuntimeTick() {
    }

    /**
     * Add game tick events here. The player must be in a world or server (if the game is paused, these events will not run).
     */
    public void clientGameTick() {
    }

    /**
     * Registers client-side only commands.
     */
    public <S> void registerClientCommands(CommandDispatcher<S> dispatcher, Function<S, ClientCommandFeedback> feedback, CommandBuildContext commandRegistryAccess) {}

    /**
     * Registers all debug entries for your mod.
     */
    public void registerDebugEntries(WritableRegistry<DebugScreenEntry> registry) {}

    /**
     * @return the menu button(s) for your mod, added to the title and pause menu screen, only if the corresponding conditions are true.
     */
    public List<PlatformMenuButton> menuButtons() {
        return List.of();
    }
}