package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.ClientForgePlatformImpl;
import net.dillon.dillonlib.platform.mixinsafe.MixinForgePlatformImpl;

/**
 * Contains all {@code Forge-specific} platforms for your convenience.
 */
public class ForgePlatforms {
    private static final MixinForgePlatformImpl MIXIN_FORGE_PLATFORM = PlatformLoader.load(MixinForgePlatformImpl.class, DillonLibMain.MOD_ID);
    private static final ClientForgePlatformImpl CLIENT_FORGE_PLATFORM = PlatformLoader.load(ClientForgePlatformImpl.class, DillonLibMain.MOD_ID);

    public static MixinForgePlatformImpl getForgeMixinPlatform() {
        return MIXIN_FORGE_PLATFORM;
    }

    public static ClientForgePlatformImpl getForgeClientPlatform() {
        return CLIENT_FORGE_PLATFORM;
    }
}