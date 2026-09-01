package net.dillon.quesoexample.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.dillonlib.util.SimplePermissions;
import net.dillon.quesoexample.platform.QuesoExamplePlatformGetter;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class QuesoCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> testCommand() {
        return Commands.literal("quesocommand")
                .requires(SimplePermissions::admin)
                .executes(
                        context -> {
                            CommonTasks.schedule(Arithmetics.S_asTick(5), () -> {
                                printMessage(context.getSource(), null, "Ran task!");
                            });
                            printMessage(context.getSource(), context.getSource().getPlayerOrException(), "Executed common-side command.");
                            return 0;
                        });
    }

    protected static void printMessage(CommandSourceStack source, ServerPlayer player, String message) {
        if (player == null) {
            QuesoExamplePlatformGetter.get().logger().info(message);
        } else {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}