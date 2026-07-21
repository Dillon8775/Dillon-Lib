package net.dillon.quesoexample.platform.client;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoexample.QuesoExampleMod;

public class ClientQuesoExamplePlatformGetter {
    private static final ClientModPlatform CLIENT_PLATFORM = PlatformLoader.load(ClientModPlatform.class, QuesoExampleMod.MOD_ID);

    public static ClientModPlatform get() {
        return CLIENT_PLATFORM;
    }
}