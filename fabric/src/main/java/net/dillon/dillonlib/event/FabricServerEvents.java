package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * {@code Server-side Fabric events} for DillonLib.
 */
@Dill(DillType.DEDICATED_SERVER)
public class FabricServerEvents {

    public static void registerFabricServerCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibEvents.registerAllServerCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}