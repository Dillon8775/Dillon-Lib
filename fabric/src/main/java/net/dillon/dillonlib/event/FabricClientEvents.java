package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * {@code Client-side Fabric events} for DillonLib.
 */
@Dill(env = DillEnv.CLIENT)
public class FabricClientEvents {

    public static void registerFabricClientCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibEvents.registerAllClientCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}