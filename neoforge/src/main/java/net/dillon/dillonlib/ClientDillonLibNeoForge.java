package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.screen.DillonLibMainMenuScreen;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibMain;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static net.dillon.dillonlib.task.ClientTasks.getLocalPlayer;

@Dill(env = DillEnv.CLIENT)
@Mod(value = DillonLibMain.MOD_ID, dist = Dist.CLIENT)
public class ClientDillonLibNeoForge {

    public ClientDillonLibNeoForge(ModContainer container, IEventBus modEventBus) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mc, parent) -> new DillonLibMainMenuScreen(parent)
        );

        modEventBus.addListener((ClientTickEvent.Post event) -> {
            DillonLibClient.Events.layClientTicking(getLocalPlayer());
        });

        DillonLibClient.cInitialize();
    }
}