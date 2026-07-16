package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dillonlib", value = Dist.DEDICATED_SERVER)
public class ForgeServerEvents {

    @SubscribeEvent
    public static void registerServerCommandsForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerServerCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}