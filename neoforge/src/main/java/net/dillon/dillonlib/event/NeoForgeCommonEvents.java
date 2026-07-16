package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = "dillonlib")
public class NeoForgeCommonEvents {

    @SubscribeEvent
    public static void registerCommonCommandsNeoForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerCommonCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}