package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.command.NeoForgeClientCommandFeedback;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.ClientFactories;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Dill(env = DillEnv.CLIENT)
@EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            event.register(keyMapping);
        }
    }

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        DillonLibClient.Events.registerAllClientCommands(event.getDispatcher(), NeoForgeClientCommandFeedback::new, event.getBuildContext());
    }
}