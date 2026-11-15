package net.dillon.quesoexample.command;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.commands.CommandPermissionLevels;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@Dill(env = DillEnv.CLIENT)
public class QuesoClientCommand {

    public static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("quesoclientcommand")
                        .requires(Commands.hasPermission(CommandPermissionLevels.LEVEL_ADMINS))
                        .executes(
                                context -> {
                                    QuesoCommand.printMessage(context.getSource(), context.getSource().getPlayerOrException(), "Executed client-side command.");
//                            CommonTasks.sendUpdateMessage(context.getSource().getPlayerOrException(),
//                                    Component.literal("yes").withColor(CommonColors.RED),
//                                    "https://modrinth.com/mod/dillon-lib/versions",
//                                    CommonColors.COSMOS_PINK);
                                    return 0;
                                }
                        )
        );
    }
}