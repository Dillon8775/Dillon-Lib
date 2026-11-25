package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibServer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

@Dill(env = DillEnv.DEDICATED_SERVER)
public class FabricServerEvents {

    public static void registerFabricServerEvents() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibServer.Events.registerAllServerCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}