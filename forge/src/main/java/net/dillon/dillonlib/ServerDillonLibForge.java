package net.dillon.dillonlib;

import net.dillon.dillonlib.main.ServerMain;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ServerDillonLibForge {

    protected static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ServerMain.sInitialize();
    }
}