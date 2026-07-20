package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibMain;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Dill(DillType.CLIENT)
@Mod(value = DillonLibMain.MOD_ID, dist = Dist.CLIENT)
public class ClientDillonLibNeoForge {

    public ClientDillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::clientSetup);

        DillonLibClient.cInitialize();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }
}