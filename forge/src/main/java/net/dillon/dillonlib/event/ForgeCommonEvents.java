package net.dillon.dillonlib.event;

import net.dillon.dillonlib.core.DillonLibEvents;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DillonLibMain.MOD_ID)
public class ForgeCommonEvents {

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