package net.dillon.dillonlib.client.command;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.network.chat.Component;

/**
 * Exposure for building client commands.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT, type = DillType.UNIVERSAL)
public interface ClientCommandFeedback {
    void sendSuccess(Component message);
    void sendFailure(Component message);
}