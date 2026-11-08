package net.dillon.dillonlib.platform.universal;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.KeyMapping;

@Dill(env = DillEnv.CLIENT)
public class NeoForgeClientUniversalPlatformImpl extends ClientUniversalModPlatform {

    // For NeoForge, you must use RegisterKeyMappingsEvent manually to register the created keybind. This is done automatically for any keybind in NeoForgeClientEvents.
    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return new KeyMapping(name, value, category);
    }
}
