package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FabricClientEvents {

    public static void registerFabricClientCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            PlatformGetter.get().registerClientCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}