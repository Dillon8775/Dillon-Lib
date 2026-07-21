package net.dillon.quesoexample;

import net.dillon.quesoexample.entity.QuesoEntityTypes;
import net.dillon.quesoexample.item.QuesoTestItems;
import net.dillon.quesoexample.platform.QuesoExamplePlatformGetter;
import net.fabricmc.api.ModInitializer;

public class QuesoExampleMod implements ModInitializer {
    public static final String MOD_ID = "quesoexample";

    @Override
    public void onInitialize() {
        QuesoEntityTypes.i_();
        QuesoTestItems.i_();

        QuesoExamplePlatformGetter.get().logger().info("Successfully initialized QuesoExample mod for DillonLib.");
    }
}