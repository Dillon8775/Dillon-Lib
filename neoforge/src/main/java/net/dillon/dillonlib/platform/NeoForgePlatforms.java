package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.ClientNeoForgePlatformImpl;
import net.dillon.dillonlib.platform.mixinsafe.MixinNeoForgePlatformImpl;

/**
 * Contains all {@code NeoForge-specific} platforms for your convenience.
 */
public class NeoForgePlatforms {
    private static final MixinNeoForgePlatformImpl MIXIN_NEOFORGE_PLATFORM = PlatformLoader.load(MixinNeoForgePlatformImpl.class, DillonLibMain.MOD_ID);
    private static final ClientNeoForgePlatformImpl CLIENT_NEOFORGE_PLATFORM = PlatformLoader.load(ClientNeoForgePlatformImpl.class, DillonLibMain.MOD_ID);

    public static MixinNeoForgePlatformImpl getFabricMixinPlatform() {
        return MIXIN_NEOFORGE_PLATFORM;
    }

    public static ClientNeoForgePlatformImpl getFabricClientPlatform() {
        return CLIENT_NEOFORGE_PLATFORM;
    }
}