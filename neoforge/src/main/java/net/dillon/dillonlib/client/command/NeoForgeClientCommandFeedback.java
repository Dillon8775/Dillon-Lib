package net.dillon.dillonlib.client.command;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

/**
 * Exposure for registering client-side neoforge commands.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public class NeoForgeClientCommandFeedback implements ClientCommandFeedback {
    private final CommandSourceStack source;

    public NeoForgeClientCommandFeedback(CommandSourceStack source) {
        this.source = source;
    }

    @Override
    public void sendSuccess(Component message) {
        source.sendSuccess(() -> message, false);
    }

    @Override
    public void sendFailure(Component message) {
        source.sendFailure(message);
    }
}