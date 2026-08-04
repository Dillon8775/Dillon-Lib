package net.dillon.quesoexample.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.dillonlib.util.SimplePermissions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

@Dill(DillType.CLIENT)
public class QuesoClientCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> clientTestCommand() {
        return Commands.literal("quesoclientcommand")
                .requires(SimplePermissions::admin)
                .executes(
                        context -> {
                            QuesoCommand.printMessage(context.getSource(), context.getSource().getPlayerOrException(), "Executed client-side command.");
                            CommonTasks.sendUpdateMessage(context.getSource().getPlayerOrException(),
                                    Component.literal("yes").withColor(CommonColors.RED),
                                    "https://modrinth.com/mod/dillon-lib/versions",
                                    CommonColors.COSMOS_PINK);
                            return 0;
                        });
    }
}