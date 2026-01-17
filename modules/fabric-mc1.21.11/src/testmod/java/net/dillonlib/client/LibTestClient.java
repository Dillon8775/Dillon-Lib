package net.dillonlib.client;

import net.dillonlib.main.DillonLib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LibTestClient {

    public static void init() {
        DillonLib.error("Initialized LibTestClient");
    }
}