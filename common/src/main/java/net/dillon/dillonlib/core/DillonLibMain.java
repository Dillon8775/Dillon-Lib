package net.dillon.dillonlib.core;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.util.UpdateChecker;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * {@code Main} initialization entrypoint for DillonLib.
 */
public class DillonLibMain {
    public static final String MOD_ID = "dillonlib";
    public static final Logger LOGGER = LoggerFactory.getLogger("DillonLib/Main");
    public static boolean HAS_UPDATE = UpdateChecker.hasUpdate(UpdateChecker.checkForUpdate(
            "dillon-lib",
            Platforms.getDillonLibPlatform().modVersion())
    );

    /**
     * Initializes DillonLib on {@code all environment sides}.
     */
    public static void initialize() {
        DillonLibOptions.INSTANCE.load();

        DillonLibMain.commonInitializers().forEach(Runnable::run);

        DillonLibEvents.registerAllEvents();

        int platforms = PlatformLoader.executeForEachPlatform(modPlatform -> {
            if (!modPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                LOGGER.info("ModPlatform loaded with mod ID {} (version {})",
                        modPlatform.modId(),
                        modPlatform.modVersion()
                );
            }
        });
        int mixinPlatforms = PlatformLoader.executeForEachMixinPlatform(mixinModPlatform -> {
            if (!mixinModPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                LOGGER.info("MixinModPlatform loaded with mod ID {} (factories={}, fullBright={})",
                        mixinModPlatform.modId(),
                        mixinModPlatform.shouldApplyFactories(),
                        mixinModPlatform.shouldApplyFullBright()
                );
            }
        });

        LOGGER.info("Loaded {} platforms and {} mixin platforms",
                platforms,
                mixinPlatforms
        );

        LOGGER.info("DillonLib {} for {} has loaded",
                Platforms.getDillonLibPlatform().modVersion(),
                Platforms.getDillonLibPlatform().platform().toString().toLowerCase(Locale.ROOT));
    }

    /**
     * @return an identifier from DillonLib.
     */
    public static Identifier dillonLibIdentifier(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
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