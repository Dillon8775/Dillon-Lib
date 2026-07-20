package net.dillon.quesoinstance.platform.mixinsafe;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.quesoinstance.QuesoInstance;

public class MixinQuesoInstanceGetter {
    private static final MixinModPlatform CLIENT_PLATFORM = PlatformLoader.load(MixinModPlatform.class, QuesoInstance.MOD_ID);

    public static MixinModPlatform get() {
        return CLIENT_PLATFORM;
    }
}