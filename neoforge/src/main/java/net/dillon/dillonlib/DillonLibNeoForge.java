package net.dillon.dillonlib;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.universal.NeoForgeUniversalPlatformImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@Mod(DillonLibMain.MOD_ID)
public final class DillonLibNeoForge {

    public DillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener((ServerTickEvent event) -> {
            DillonLibMain.Events.layCommonTicking();
        });

        modEventBus.addListener(NeoForgeUniversalPlatformImpl::addItemsToTab);
        NeoForgeUniversalPlatformImpl.ITEM_GROUPS.register(modEventBus);

        DillonLibMain.initialize();
    }
}