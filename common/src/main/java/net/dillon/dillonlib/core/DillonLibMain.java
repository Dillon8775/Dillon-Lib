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

        DillonLibEvents.registerDispenserBehaviors();

        DillonLibEvents.registerAllEvents();

        int platforms = PlatformLoader.executeForEachPlatform(modPlatform ->
                PlatformGetter.getDillonLibPlatform().logger().info("ModPlatform loaded with mod ID {} (version {})",
                        modPlatform.modId(),
                        modPlatform.modVersion()
                ));
        int mixinPlatforms = PlatformLoader.executeForEachMixinPlatform(mixinModPlatform ->
                PlatformGetter.getDillonLibPlatform().logger().info("MixinModPlatform loaded with mod ID {} (factories={}, fullBright={})",
                        mixinModPlatform.modId(),
                        mixinModPlatform.shouldApplyFactories(),
                        mixinModPlatform.shouldApplyFullBright()
                ));

        PlatformGetter.getDillonLibPlatform().logger().info("Loaded {} platforms and {} mixin platforms",
                platforms,
                mixinPlatforms
        );

        PlatformGetter.getDillonLibPlatform().logger().info("DillonLib {} for {} has loaded",
                PlatformGetter.getDillonLibPlatform().modVersion(),
                PlatformGetter.getDillonLibPlatform().platformName().toString().toLowerCase(Locale.ROOT));
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