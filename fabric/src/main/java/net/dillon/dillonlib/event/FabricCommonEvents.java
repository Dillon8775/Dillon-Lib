package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FabricCommonEvents {

    public static void registerFabricCommonCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            PlatformGetter.get().registerCommonCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}