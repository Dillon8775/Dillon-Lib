package net.dillon.quesoexample.platform;

import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.quesoexample.QuesoExampleMod;

public class QuesoExamplePlatformGetter {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, QuesoExampleMod.MOD_ID);

    public static ModPlatform get() {
        return PLATFORM;
    }
}