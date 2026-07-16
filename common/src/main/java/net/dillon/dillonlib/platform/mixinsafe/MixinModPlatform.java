package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.platform.ModReference;

/**
 * A separate service loader for mixin-safe classes and plugins, to prevent unknown and unexpected crashes when reading other Minecraft files during game initialization.
 */
public abstract class MixinModPlatform {

    /**
     * @return if a mod is loaded on a specific platform.
     */
    public abstract boolean isModLoaded(ModReference mod);
}