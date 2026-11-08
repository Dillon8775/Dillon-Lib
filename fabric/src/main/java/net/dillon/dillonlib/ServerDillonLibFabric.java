package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibServer;
import net.dillon.dillonlib.event.FabricServerEvents;
import net.fabricmc.api.DedicatedServerModInitializer;

@Dill(env = DillEnv.DEDICATED_SERVER)
public class ServerDillonLibFabric implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        FabricServerEvents.registerFabricServerCommands();

        DillonLibServer.sInitialize();
    }
}