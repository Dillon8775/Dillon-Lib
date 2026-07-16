package net.dillon.dillonlib;

import net.dillon.dillonlib.main.ClientMain;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientDillonLibForge {

    protected static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(ClientDillonLibForge::clientSetup);

        ClientMain.cInitialize();
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
    }
}