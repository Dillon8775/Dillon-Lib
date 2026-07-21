package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.core.DillonLibServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Dill(DillType.DEDICATED_SERVER)
@Mod(value = DillonLibMain.MOD_ID, dist = Dist.DEDICATED_SERVER)
public class ServerDillonLibNeoForge {

    public ServerDillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::serverSetup);

        DillonLibServer.sInitialize();
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
    }
}