package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.ClientFabricPlatformImpl;
import net.dillon.dillonlib.platform.mixinsafe.MixinFabricPlatformImpl;

/**
 * Contains all {@code Fabric-specific} platforms for your convenience.
 */
public class FabricPlatforms {
    private static final MixinFabricPlatformImpl MIXIN_FABRIC_PLATFORM = PlatformLoader.load(MixinFabricPlatformImpl.class, DillonLibMain.MOD_ID);
    private static final ClientFabricPlatformImpl CLIENT_FABRIC_PLATFORM = PlatformLoader.load(ClientFabricPlatformImpl.class, DillonLibMain.MOD_ID);

    public static MixinFabricPlatformImpl getFabricMixinPlatform() {
        return MIXIN_FABRIC_PLATFORM;
    }

    public static ClientFabricPlatformImpl getFabricClientPlatform() {
        return CLIENT_FABRIC_PLATFORM;
    }
}