
package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.event.FabricClientEvents;
import net.fabricmc.api.ClientModInitializer;

@Dill(env = DillEnv.CLIENT)
public class ClientDillonLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricClientEvents.registerFabricClientCommands();
        FabricClientEvents.registerFabricBoatRenderers();

        DillonLibClient.cInitialize();
    }
}