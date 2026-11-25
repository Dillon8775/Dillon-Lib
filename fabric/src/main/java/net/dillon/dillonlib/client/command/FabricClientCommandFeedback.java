package net.dillon.dillonlib.client.command;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

/**
 * Exposure for registering client-side fabric commands.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public class FabricClientCommandFeedback implements ClientCommandFeedback {
    private final FabricClientCommandSource source;

    public FabricClientCommandFeedback(FabricClientCommandSource source) {
        this.source = source;
    }

    @Override
    public void sendSuccess(Component message) {
        source.sendFeedback(message);
    }

    @Override
    public void sendFailure(Component message) {
        source.sendError(message);
    }
}