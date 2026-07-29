package net.dillon.dillonlib.event;

import net.dillon.dillonlib.core.DillonLibEvents;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.Platforms;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = DillonLibMain.MOD_ID)
public class NeoForgeCommonEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent dispatcher) {
        DillonLibEvents.registerAllCommonCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());

        if (Platforms.getCommonPlatform().isEnvironmentClient()) {
            DillonLibEvents.registerAllClientCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
        }

        if (Platforms.getCommonPlatform().isEnvironmentServer()) {
            DillonLibEvents.registerAllServerCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
        }
    }
}