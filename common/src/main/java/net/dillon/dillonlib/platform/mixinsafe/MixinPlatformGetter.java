package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.PlatformLoader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * The mixin-safe multi-loader platform getter for mixin methods.
 * @see MixinModPlatform
 */
public class MixinPlatformGetter {
    private static final MixinModPlatform MIXIN_PLATFORM = PlatformLoader.load(MixinModPlatform.class, DillonLibMain.MOD_ID);

    /**
     * @return the mixin mod platform for DillonLib.
     */
    public static MixinModPlatform getDillonLibMixinPlatform() {
        return MIXIN_PLATFORM;
    }

    /**
     * @return if {@code factories} should be applied into the game, which determines if certain mixin fixes should be applied.
     */
    public static boolean shouldApplyFactories() {
        try {
            List<MixinModPlatform> mixinPlatforms = ServiceLoader.load(MixinModPlatform.class)
                    .stream()
                    .map(ServiceLoader.Provider::get)
                    .filter(MixinModPlatform::shouldApplyFactories)
                    .toList();

            // Meaning if a mixin platform that returns true for "shouldApplyFactories" is found, factories should be applied unless mixins are manually disabled
            if (!mixinPlatforms.isEmpty()) {
                return true;
            }
        } catch (NoSuchElementException o) {
            return false;
        }

        return false;
    }
}