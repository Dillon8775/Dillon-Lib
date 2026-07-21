package net.dillon.dillonlib.event;

import net.dillon.dillonlib.core.DillonLibEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * {@code Common Fabric events} for DillonLib.
 */
public class FabricCommonEvents {

    public static void registerFabricCommonCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibEvents.registerAllCommonCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}