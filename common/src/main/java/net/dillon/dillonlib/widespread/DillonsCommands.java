package net.dillon.dillonlib.widespread;

import net.minecraft.commands.CommandPermissionLevels;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * Common command methods that can be used throughout your mod.
 * @since 1.3
 */
public class DillonsCommands {

    /**
     * Sends the player a message that they do not have permission to execute the command, and returns 0 for valid {@link CommandSourceStack}s.
     */
    public static int sendNoPermissionMessage(CommandSourceStack source) {
        source.sendFailure(Component.literal("You do not have permission to execute this command."));
        return 0;
    }

    /**
     * @return if the source has admin permissions, or is a non-player source like a command block.
     */
    public static boolean hasAdminPermissionsOrCommandSource(CommandSourceStack source) {
        return Commands.hasPermission(CommandPermissionLevels.LEVEL_ADMINS)
                .test()
                .check(source.permissions())
                || !source.isPlayer();
    }
}