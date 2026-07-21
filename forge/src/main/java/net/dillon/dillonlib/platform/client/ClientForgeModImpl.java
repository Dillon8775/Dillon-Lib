package net.dillon.dillonlib.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraft.client.KeyMapping;

public class ClientForgeModImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public KeyMapping createKeyMapping(String name, InputConstants.Type type, String category, int value) {
        return new KeyMapping(name, value, category);
    }
}