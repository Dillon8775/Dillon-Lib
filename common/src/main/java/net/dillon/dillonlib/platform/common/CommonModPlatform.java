package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.TaskScheduler;
import net.dillon.dillonlib.platform.Loadable;
import net.dillon.dillonlib.platform.ModPlatform;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * Platform class which always returns the same value for specific platforms, meaning it's not customizable by your mod. This class is not an instance of loadable, as it does not check for multiple instances of these classes. You should not create your own instance of this platform, because this platform is meant to be final.
 * @since 1.0
 * @see Loadable
 * @see ModPlatform
 */
public abstract class CommonModPlatform {

    /**
     * Gets the config directory for the supported platform.
     */
    public abstract @NotNull Path configDir();

    /**
     * @return if the environment side is client.
     */
    public abstract boolean isEnvironmentClient();

    /**
     * @return if the environment side is server.
     */
    public abstract boolean isEnvironmentServer();

    /**
     * @return if the game was launched in a development environment.
     */
    public abstract boolean isDevelopmentEnvironment();

    /**
     * Ticks all simple scheduled tasks.
     * @see TaskScheduler
     */
    public abstract void tickScheduledTasks();
}