package net.dillon.quesoexample.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.util.SimplePermissions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@Dill(env = DillEnv.DEDICATED_SERVER)
public class QuesoServerCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> serverTestCommand() {
        return Commands.literal("quesoservercommand")
                .requires(SimplePermissions::notPlayer)
                .executes(
                        context -> {
                            QuesoCommand.printMessage(context.getSource(), null, "Executed server-side command.");
                            return 0;
                        });
    }
}