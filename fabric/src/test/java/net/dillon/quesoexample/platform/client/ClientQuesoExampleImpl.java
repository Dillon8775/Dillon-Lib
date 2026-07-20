package net.dillon.quesoexample.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoexample.QuesoExampleMod;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class ClientQuesoExampleImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public KeyMapping createKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return KeyBindingHelper.registerKeyBinding(new KeyMapping(name, value, category));
    }
}