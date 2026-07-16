package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = "dillonlib", value = Dist.DEDICATED_SERVER)
public class NeoForgeServerEvents {

    @SubscribeEvent
    public static void registerServerCommandsNeoForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerServerCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}