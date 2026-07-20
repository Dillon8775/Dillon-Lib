package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;

/**
 * The main platform getter for DillonLib. You need to make your own platform getter for your mod that is linked to your mod id!
 * @see ModPlatform
 */
public class PlatformGetter {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, DillonLibMain.MOD_ID);

    /**
     * The mod's multi-loader platform.
     * In this example we provide a platform helper which provides information about what platform the mod is running on.
     * For example this can be used to check if the code is running on Forge vs Fabric, or to ask the ModLoader if another
     * mod is loaded.
     * @return the main platform, specifically for DillonLib. You should create your own {@code get} method to get the correct platform for your mod.
     */
    public static ModPlatform getDillonLibPlatform() {
        return PLATFORM;
    }
}