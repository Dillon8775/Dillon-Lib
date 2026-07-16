package net.dillon.dillonlib;

import net.dillon.dillonlib.event.FabricClientEvents;
import net.dillon.dillonlib.main.ClientMain;
import net.fabricmc.api.ClientModInitializer;

public class ClientDillonLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricClientEvents.registerFabricClientCommands();
        ClientMain.cInitialize();
    }
}