package net.dillon.dillonlib;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.event.FabricCommonEvents;
import net.fabricmc.api.ModInitializer;

public class DillonLibFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FabricCommonEvents.registerFabricCommonCommands();

        DillonLibMain.initialize();
    }
}