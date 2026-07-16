package net.dillon.dillonlib.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * A platform helper class for Dillon's mods. Can be used for various different calls and functions, and new methods can easily be added in the mod's specified platform.
 * <p>Each platform for your mod (ex. fabric and neoforge) should contain two service loaders, one for this class and one for the {@code MixinModPlatform}, which is a mixin-safe class for methods that can be used in conditional mixin plugins.</p>
 */
public abstract class ModPlatform {

    /**
     * @return the logger for the mod.
     */
    public abstract @NotNull Logger logger();

    /**
     * @return the mod id.
     */
    public abstract String modId();

    /**
     * @return the mod version.
     */
    public abstract String version();

    /**
     * @return the platform name.
     */
    public abstract @NotNull PlatformName platformName();

    /**
     * @return the platform release.
     */
    public abstract @NotNull PlatformRelease platformRelease();

    /**
     * @return the logo width.
     */
    public abstract @NotNull LogoWidth logoWidth();

    /**
     * Gets the config directory for the supported platform.
     */
    public abstract @NotNull Path configDir();

    /**
     * @return if a {@code Quality of Queso} packet can be sent.
     */
    public abstract boolean canSendPacket(LocalPlayer localPlayer);

    /**
     * Registers commands that should be on all environments. Can be blank.
     */
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    /**
     * Registers client-side only commands. Can be blank.
     */
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    /**
     * Registers server-side only commands. Can be blank.
     */
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    /**
     * Sends a normal {@code message} to console.
     */
    public static void info(String message) {
        PlatformGetter.get().logger().info(message);
    }

    /**
     * Sends a {@code warning} message to console.
     */
    public static void warn(String message) {
        PlatformGetter.get().logger().warn(message);
    }

    /**
     * Sends a {@code error} message to console.
     */
    public static void error(String error) {
        PlatformGetter.get().logger().error(error);
    }

    /**
     * Sends a {@code debug} message to console
     */
    public static void debug(String message) {
        PlatformGetter.get().logger().debug(message);
    }
}