package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.event.FabricClientEvents;
import net.dillon.dillonlib.main.ClientMain;
import net.fabricmc.api.ClientModInitializer;

@Dill(DillType.CLIENT)
public class ClientDillonLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricClientEvents.registerFabricClientCommands();
        ClientMain.cInitialize();
    }
}