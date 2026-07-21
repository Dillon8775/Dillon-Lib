package net.dillon.dillonlib.platform.common;

import java.util.ServiceLoader;

/**
 * The common platform getter for commonly used methods that are not customizable by your or other mods.
 * @since 1.0
 * @see CommonPlatformGetter
 */
public class CommonPlatformGetter {
    private static final CommonModPlatform COMMON_PLATFORM = loadCommonPlatform();

    /**
     * @return the common platform for DillonLib, which should be used in your mod, as they are effectively final.
     */
    public static CommonModPlatform get() {
        return COMMON_PLATFORM;
    }

    /**
     * Loads the common mod platform.
     */
    private static CommonModPlatform loadCommonPlatform() {
        return ServiceLoader.load(CommonModPlatform.class)
                .findFirst()
                .orElseThrow();
    }
}