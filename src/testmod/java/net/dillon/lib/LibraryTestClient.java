package net.dillon.lib;

import net.dillon.lib.client.LibTestClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LibraryTestClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LibTestClient.init();
    }
}