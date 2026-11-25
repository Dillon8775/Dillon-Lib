package net.dillon.dillonlib.event;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.core.DillonLibServer;
import net.dillon.dillonlib.platform.Platforms;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = DillonLibMain.MOD_ID)
public class NeoForgeCommonEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent dispatcher) {
        DillonLibMain.Events.registerAllCommonCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());

        if (Platforms.getUniversalPlatform().isEnvironmentServer()) {
            DillonLibServer.Events.registerAllServerCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
        }
    }
}