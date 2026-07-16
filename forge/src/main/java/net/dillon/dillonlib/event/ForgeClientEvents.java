package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dillonlib", value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent
    public static void registerClientCommandsForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerClientCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}