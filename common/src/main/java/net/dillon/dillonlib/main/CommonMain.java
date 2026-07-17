package net.dillon.dillonlib.main;

import net.dillon.dillonlib.*;
import net.dillon.dillonlib.platform.Statics;

import java.util.List;
import java.util.Locale;

/**
 * {@code Main} initialization entrypoint for DillonLib.
 */
public class CommonMain {

    /**
     * Initializes DillonLib on {@code all environment sides}.
     */
    public static void initialize() {
        commonInitializers().forEach(Runnable::run);

        Statics.getLogger().info("DillonLib {} for {} has successfully initialized.", Statics.MOD_VERSION, Statics.PLATFORM_NAME.toString().toLowerCase(Locale.ROOT));
    }

    /**
     * @return all common initializer methods for {@code universal environment classes.}
     */
    private static List<Runnable> commonInitializers() {
        return List.of(
                Arithmetics::i_,
                BaseOptions::i_,
                PlayerStorage::i_,
                TaskScheduler::i_,
                Texts::i_
        );
    }
}