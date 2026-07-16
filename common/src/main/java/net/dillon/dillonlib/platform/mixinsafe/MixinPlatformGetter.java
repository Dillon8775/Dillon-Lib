package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.platform.ModPlatform;

import java.util.ServiceLoader;

/**
 * The mixin-safe multi-loader platform getter for mixin methods. See {@code ModPlatform} class for more.
 */
public class MixinPlatformGetter {
    private static final ModPlatform PLATFORM = load();

    public static ModPlatform get() {
        return PLATFORM;
    }

    protected static <T> T load() {
        return ServiceLoader.load((Class<T>) ModPlatform.class)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + ModPlatform.class.getName()));
    }
}