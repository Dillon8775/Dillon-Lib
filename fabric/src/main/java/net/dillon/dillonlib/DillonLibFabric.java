package net.dillon.dillonlib;

import net.dillon.dillonlib.event.FabricCommonEvents;
import net.dillon.dillonlib.main.CommonMain;
import net.fabricmc.api.ModInitializer;

public class DillonLibFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FabricCommonEvents.registerFabricCommonCommands();
        CommonMain.initialize();
    }
}