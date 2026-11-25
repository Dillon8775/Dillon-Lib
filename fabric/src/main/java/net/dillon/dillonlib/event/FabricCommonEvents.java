package net.dillon.dillonlib.event;

import net.dillon.dillonlib.core.DillonLibMain;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class FabricCommonEvents {

    public static void registerFabricCommonEvents() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibMain.Events.registerAllCommonCommands(commandDispatcher, commandRegistryAccess);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            DillonLibMain.Events.layCommonTicking();
        });
    }
}