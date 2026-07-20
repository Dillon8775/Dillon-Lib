package net.dillon.quesoinstance;

import net.dillon.quesoinstance.platform.QuesoInstancePlatformGetter;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(QuesoInstance.MOD_ID)
public final class QuesoInstance {
    public static final String MOD_ID = "quesoinstance";

    public QuesoInstance(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);

        QuesoInstancePlatformGetter.get().logger().info("Successfully initialized QuesoInstance mod for DillonLib (neoforge).");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}