package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.ClientFactories;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

/**
 * {@code Client-side NeoForge events} for DillonLib.
 */
@Dill(DillType.CLIENT)
@EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            event.register(keyMapping);
        }
    }
}