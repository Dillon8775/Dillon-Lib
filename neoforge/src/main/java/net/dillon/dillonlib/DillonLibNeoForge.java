package net.dillon.dillonlib;

import net.dillon.dillonlib.main.CommonMain;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod("dillonlib")
public final class DillonLibNeoForge {

    public DillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        CommonMain.initialize();
    }
}