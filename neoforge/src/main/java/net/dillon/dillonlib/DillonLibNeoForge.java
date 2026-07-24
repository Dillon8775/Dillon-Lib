package net.dillon.dillonlib;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.common.NeoForgeCommonPlatformImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(DillonLibMain.MOD_ID)
public final class DillonLibNeoForge {

    public DillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(NeoForgeCommonPlatformImpl::addItemsToTab);

        NeoForgeCommonPlatformImpl.ITEM_GROUPS.register(modEventBus);

        DillonLibMain.initialize();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}