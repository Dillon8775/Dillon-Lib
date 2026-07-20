package net.dillon.quesoinstance.platform;

import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.quesoinstance.QuesoInstance;

public class QuesoInstancePlatformGetter {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, QuesoInstance.MOD_ID);

    public static ModPlatform get() {
        return PLATFORM;
    }
}