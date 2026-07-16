package net.dillon.dillonlib;

import net.dillon.dillonlib.main.ClientMain;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = "dillonlib", dist = Dist.CLIENT)
public class ClientDillonLibNeoForge {

    public ClientDillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::clientSetup);
        ClientMain.cInitialize();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }
}