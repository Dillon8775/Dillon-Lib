package net.dillon.dillonlib.event;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dillonlib")
public class ForgeCommonEvents {

    @SubscribeEvent
    public static void registerCommonCommandsForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerCommonCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}