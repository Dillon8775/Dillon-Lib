package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FabricServerEvents {

    public static void registerFabricServerCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            PlatformGetter.get().registerServerCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}