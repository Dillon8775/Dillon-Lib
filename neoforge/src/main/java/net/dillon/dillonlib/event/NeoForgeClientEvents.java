package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * {@code Client-side NeoForge events} for DillonLib.
 */
@Dill(DillType.CLIENT)
@EventBusSubscriber(modid = "dillonlib", value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void registerClientCommandsNeoForge(RegisterCommandsEvent dispatcher) {
        PlatformGetter.get().registerClientCommands(dispatcher.getDispatcher(), dispatcher.getBuildContext());
    }
}