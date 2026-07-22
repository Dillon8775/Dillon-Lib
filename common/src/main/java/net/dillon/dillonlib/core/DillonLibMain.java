package net.dillon.dillonlib.core;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.dillon.dillonlib.platform.PlatformLoader;

import java.util.List;
import java.util.Locale;

/**
 * {@code Main} initialization entrypoint for DillonLib.
 */
public class DillonLibMain {
    public static final String MOD_ID = "dillonlib";

    /**
     * Initializes DillonLib on {@code all environment sides}.
     */
    public static void initialize() {
        DillonLibMain.commonInitializers().forEach(Runnable::run);

        DillonLibEvents.registerAllEvents();

        DillonLibEvents.registerDispenserBehaviors();
        DillonLibEvents.tickAllScheduledTasks();

        PlatformGetter.getDillonLibPlatform().logger().info("DillonLib {} for {} has successfully initialized.", PlatformGetter.getDillonLibPlatform().modVersion(), PlatformGetter.getDillonLibPlatform().platformName().toString().toLowerCase(Locale.ROOT));
    }

    /**
     * @return all common initializer methods for {@code universal environment classes.}
     */
    private static List<Runnable> commonInitializers() {
        return List.of(
                PlatformLoader::i_,
                Factories::i_
        );
    }
}