package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.command.FabricClientCommandFeedback;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.task.ClientTasks;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Dill(env = DillEnv.CLIENT)
public class FabricClientEvents {

    public static void registerFabricClientEvents() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext) -> {
            DillonLibClient.Events.registerAllClientCommands(dispatcher, FabricClientCommandFeedback::new, buildContext);
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            DillonLibClient.Events.layClientTicking(client.player);
        });
    }
}