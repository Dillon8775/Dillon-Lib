package net.dillon.quesoexample.command;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.dillonlib.util.TickValue;
import net.dillon.quesoexample.QuesoExampleMod;
import net.minecraft.commands.CommandPermissionLevels;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.dillon.dillonlib.task.ClientTasks.getLocalPlayer;

public class QuesoCommand {

    public static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("quesocommand")
                        .requires(Commands.hasPermission(CommandPermissionLevels.LEVEL_ADMINS))
                        .executes(
                                context -> {
                                    CommonTasks.createScheduledTask(new TickValue(5).toTicksSeconds(), () -> {
                                        printMessage(context.getSource(), null, "Ran task!");
                                    });
                                    printMessage(context.getSource(), context.getSource().getPlayerOrException(), "Executed common-side command.");
                                    return 0;
                                }
                        )
        );
    }

    protected static void printLocalMessage(String message) {
        if (getLocalPlayer() == null) {
            QuesoExampleMod.LOGGER.info(message);
        } else {
            getLocalPlayer().sendSystemMessage(Component.literal(message));
        }
    }

    protected static void printMessage(CommandSourceStack source, ServerPlayer player, String message) {
        if (player == null) {
            QuesoExampleMod.LOGGER.info(message);
        } else {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}