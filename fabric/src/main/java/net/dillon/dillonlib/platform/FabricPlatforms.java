package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.FabricClientPlatformImpl;
import net.dillon.dillonlib.platform.mixinsafe.FabricMixinPlatformImpl;

/**
 * Contains all {@code Fabric-specific} platforms for your convenience.
 */
public class FabricPlatforms {
    private static final FabricMixinPlatformImpl MIXIN_FABRIC_PLATFORM = PlatformLoader.load(FabricMixinPlatformImpl.class, DillonLibMain.MOD_ID);
    private static final FabricClientPlatformImpl CLIENT_FABRIC_PLATFORM = PlatformLoader.load(FabricClientPlatformImpl.class, DillonLibMain.MOD_ID);

    public static FabricMixinPlatformImpl getFabricMixinPlatform() {
        return MIXIN_FABRIC_PLATFORM;
    }

    public static FabricClientPlatformImpl getFabricClientPlatform() {
        return CLIENT_FABRIC_PLATFORM;
    }
}