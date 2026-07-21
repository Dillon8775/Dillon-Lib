package net.dillon.dillonlib.platform.client;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.PlatformLoader;

/**
 * The platform getter for client-side only platform.
 * @see ClientModPlatform
 */
public class ClientPlatformGetter {
    private static final ClientModPlatform PLATFORM = PlatformLoader.load(ClientModPlatform.class, DillonLibMain.MOD_ID);

    /**
     * @return the mixin mod platform for DillonLib.
     */
    public static ClientModPlatform getDillonLibClientPlatform() {
        return PLATFORM;
    }
}