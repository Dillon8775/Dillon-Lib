package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.event.FabricServerEvents;
import net.dillon.dillonlib.main.ServerMain;
import net.fabricmc.api.DedicatedServerModInitializer;

@Dill(DillType.DEDICATED_SERVER)
public class ServerDillonLibFabric implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        FabricServerEvents.registerFabricServerCommands();
        ServerMain.sInitialize();
    }
}