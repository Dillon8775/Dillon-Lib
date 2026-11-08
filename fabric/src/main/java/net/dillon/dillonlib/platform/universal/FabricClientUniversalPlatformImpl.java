package net.dillon.dillonlib.platform.universal;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

public class FabricClientUniversalPlatformImpl extends ClientUniversalModPlatform {

    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return KeyMappingHelper.registerKeyMapping(new KeyMapping(name, value, category));
    }
}