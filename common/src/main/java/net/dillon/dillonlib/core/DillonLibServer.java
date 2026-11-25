package net.dillon.dillonlib.core;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;

/**
 * {@code Server} initialization entrypoint for DillonLib.
 */
@Dill(env = DillEnv.DEDICATED_SERVER, type = DillType.CORE)
public class DillonLibServer {

    /**
     * Initializes the {@code server-side} of DillonLib.
     */
    public static void sInitialize() {
        DillonLibServer.serverInitializers().forEach(Runnable::run);

        DillonLibMain.LOGGER.info("(Server) DillonLib has loaded");
    }

    /**
     * @return all server-side initializer methods for {@code server-side only classes.}
     */
    private static List<Runnable> serverInitializers() {
        return List.of(
        );
    }

    /**
     * Event handling for the server-side of DillonLib.
     */
    public static class Events {

        /**
         * Registers all {@code server} commands.
         */
        public static void registerAllServerCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
            Platforms.forEachPlatform(modPlatform -> modPlatform.registerServerCommands(commandDispatcher, commandRegistryAccess));
        }
    }
}