package net.dillon.dillonlib;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;

/**
 * A utility class for getting simple permission levels that can be used in custom commands.
 * @since 1.0
 */
public class SimplePermissions {

    /**
     * @return if the player's permission level is equivalent to the passed in value.
     */
    public static boolean hasPermissionLevel(CommandSourceStack commandSourceStack, PermissionLevel level) {
        return commandSourceStack.permissions().hasPermission(new Permission.HasCommandLevel(level));
    }

    /**
     * @return if the player has no permissions (meaning they can use any non-op required command).
     */
    public static boolean all(CommandSourceStack commandSourceStack) {
        return hasPermissionLevel(commandSourceStack, PermissionLevel.ALL);
    }

    /**
     * @return if the player has moderator permissions.
     */
    public static boolean moderator(CommandSourceStack commandSourceStack) {
        return hasPermissionLevel(commandSourceStack, PermissionLevel.MODERATORS);
    }

    /**
     * @return if the player has gamemaster permissions.
     */
    public static boolean gamemaster(CommandSourceStack commandSourceStack) {
        return hasPermissionLevel(commandSourceStack, PermissionLevel.GAMEMASTERS);
    }

    /**
     * @return if the player has admin (or operator) permissions.
     */
    public static boolean admin(CommandSourceStack commandSourceStack) {
        return hasPermissionLevel(commandSourceStack, PermissionLevel.ADMINS);
    }

    /**
     * @return if the player has owner permissions.
     */
    public static boolean owner(CommandSourceStack commandSourceStack) {
        return hasPermissionLevel(commandSourceStack, PermissionLevel.OWNERS);
    }

    /**
     * @return the command has to be executed by console or command block (not directly by a player).
     */
    public static boolean notPlayer(CommandSourceStack commandSourceStack) {
        return !commandSourceStack.isPlayer();
    }
}