package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.ClientFactories;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Dill(DillType.CLIENT)
@Mod.EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            event.register(keyMapping);
        }
    }
}