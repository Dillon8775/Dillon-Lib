package net.dillon.quesoinstance.platform.client;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoinstance.QuesoInstance;

public class ClientQuesoInstanceGetter {
    private static final ClientModPlatform CLIENT_PLATFORM = PlatformLoader.load(ClientModPlatform.class, QuesoInstance.MOD_ID);

    public static ClientModPlatform get() {
        return CLIENT_PLATFORM;
    }
}