package net.dillon.quesoexample.platform.mixinsafe;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.quesoexample.QuesoExampleMod;

public class MixinQuesoExamplePlatformGetter {
    private static final MixinModPlatform MIXIN_PLATFORM = PlatformLoader.load(MixinModPlatform.class, QuesoExampleMod.MOD_ID);

    public static MixinModPlatform get() {
        return MIXIN_PLATFORM;
    }
}