package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.NeoForgeClientPlatformImpl;
import net.dillon.dillonlib.platform.mixinsafe.NeoForgeMixinPlatformImpl;

/**
 * Contains all {@code NeoForge-specific} platforms for your convenience.
 */
public class NeoForgePlatforms {
    private static final NeoForgeMixinPlatformImpl MIXIN_NEOFORGE_PLATFORM = PlatformLoader.load(NeoForgeMixinPlatformImpl.class, DillonLibMain.MOD_ID);
    private static final NeoForgeClientPlatformImpl CLIENT_NEOFORGE_PLATFORM = PlatformLoader.load(NeoForgeClientPlatformImpl.class, DillonLibMain.MOD_ID);

    public static NeoForgeMixinPlatformImpl getFabricMixinPlatform() {
        return MIXIN_NEOFORGE_PLATFORM;
    }

    public static NeoForgeClientPlatformImpl getFabricClientPlatform() {
        return CLIENT_NEOFORGE_PLATFORM;
    }
}