package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.platform.Loadable;
import net.dillon.dillonlib.platform.info.ModReference;

/**
 * A separate service loader for mixin-safe classes and plugins, to prevent unknown and unexpected crashes when reading other Minecraft files during game initialization and checking mixins. This class should only be used within mixin plugins to ensure stability.
 * @since 1.0
 * @see MixinPluginUtil
 */
public abstract class MixinModPlatform implements Loadable {

    /**
     * @return the mod id for this platform. Should return your mod id from your common mod class.
     */
    @Override
    public abstract String modId();

    /**
     * @return if a mod is loaded on a specific platform. You can use this in {@link MixinPluginUtil}s, or anywhere else, but call {@code FabricPlatforms} or {@code (Neo)ForgePlatforms} to get the correct return value of this method!
     */
    public boolean isModLoaded(ModReference mod) {
        return false;
    }

    /**
     * Specifies if factories should be applied. If any instance of {@link MixinModPlatform} returns {@code true} here, unless a mixin is manually disabled, factories will be applied.
     * @return {@code false} by default because not all of Dillon's mods use factories. This returns false by default as a safety feature in order to prevent mod incompatibility with other mods when factories aren't even being used.
     * You must {@code override this to return true} if you plan on using {@link net.dillon.dillonlib.factory}s!
     */
    public boolean shouldApplyFactories() {
        return false;
    }

    /**
     * @return if full bright settings should be applied. This allows for full bright in your mod if you wish to add it.
     */
    public boolean shouldApplyFullBright() {
        return false;
    }
}