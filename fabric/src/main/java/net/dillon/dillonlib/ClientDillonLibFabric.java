package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.event.FabricClientEvents;
import net.fabricmc.api.ClientModInitializer;

@Dill(DillType.CLIENT)
public class ClientDillonLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricClientEvents.registerFabricJoinLeaveEvents();
        FabricClientEvents.registerFabricClientCommands();
        FabricClientEvents.registerFabricBoatRenderers();

        DillonLibClient.cInitialize();
    }
}