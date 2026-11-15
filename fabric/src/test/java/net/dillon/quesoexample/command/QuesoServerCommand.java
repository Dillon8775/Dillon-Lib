package net.dillon.quesoexample.command;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@Dill(env = DillEnv.DEDICATED_SERVER)
public class QuesoServerCommand {

    public static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("quesoservercommand")
                        .executes(
                                context -> {
                                    QuesoCommand.printMessage(context.getSource(), null, "Executed server-side command.");
                                    return 0;
                                }
                        )
        );
    }
}