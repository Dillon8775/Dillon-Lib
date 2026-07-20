package net.dillon.quesoexample.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.SimplePermissions;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@Dill(DillType.CLIENT)
public class QuesoClientCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> clientTestCommand() {
        return Commands.literal("quesoclientcommand")
                .requires(SimplePermissions::admin)
                .executes(
                        context -> {
                            QuesoCommand.printMessage(context.getSource(), context.getSource().getPlayerOrException(), "Executed client-side command.");
                            return 0;
                        });
    }
}