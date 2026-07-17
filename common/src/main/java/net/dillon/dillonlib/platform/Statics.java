package net.dillon.dillonlib.platform;

import net.minecraft.client.player.LocalPlayer;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * Common static methods that can be used for easier access to the mod's information.
 */
public class Statics {
    public static final String MOD_ID = PlatformGetter.get().modId();
    public static final String MOD_VERSION = PlatformGetter.get().modVersion();
    public static final int LOGO_WIDTH = PlatformGetter.get().logoWidth().getWidthModifier();
    public static final Path CONFIG_DIR = PlatformGetter.get().configDir();
    public static final PlatformName PLATFORM_NAME = PlatformGetter.get().platformName();
    public static final PlatformRelease PLATFORM_RELEASE = PlatformGetter.get().platformRelease();

    /**
     * @return if the player is able to send a modded packet.
     */
    public static boolean canSendPacket(LocalPlayer player) {
        return PlatformGetter.get().canSendPacket(player);
    }

    /**
     * @return the current logger.
     */
    public static Logger getLogger() {
        return PlatformGetter.get().logger();
    }

    /**
     * Sends a normal {@code message} to the console.
     */
    public static void info(String message) {
        PlatformGetter.get().logger().info(message);
    }

    /**
     * Sends a {@code warning} message to the console.
     */
    public static void warn(String message) {
        PlatformGetter.get().logger().warn(message);
    }

    /**
     * Sends a {@code error} message to the console.
     */
    public static void error(String error) {
        PlatformGetter.get().logger().error(error);
    }

    /**
     * Sends a {@code debug} message to the console.
     */
    public static void debug(String message) {
        PlatformGetter.get().logger().debug(message);
    }
}