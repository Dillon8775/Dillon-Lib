package net.dillon.quesoexample.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.command.ClientCommandFeedback;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

@Dill(env = DillEnv.CLIENT)
public class QuesoClientCommand{

    public static <S> LiteralArgumentBuilder<S> create(Function<S, ClientCommandFeedback> feedback) {
        return LiteralArgumentBuilder.<S>literal("quesoclientcommand")
                .executes(
                        context -> {
                            feedback.apply(context.getSource()).sendSuccess(Component.literal("Executed client-side command."));
//                            CommonTasks.sendUpdateMessage(context.getSource().getPlayerOrException(),
//                                    Component.literal("yes").withColor(CommonColors.RED),
//                                    "https://modrinth.com/mod/dillon-lib/versions",
//                                    CommonColors.COSMOS_PINK);
                            return 0;
                        }
                );
    }
}