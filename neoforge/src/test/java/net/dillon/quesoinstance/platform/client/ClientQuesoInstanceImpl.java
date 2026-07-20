package net.dillon.quesoinstance.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoinstance.QuesoInstance;
import net.minecraft.client.KeyMapping;

public class ClientQuesoInstanceImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return QuesoInstance.MOD_ID;
    }

    @Override
    public KeyMapping createKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return new KeyMapping(name, value, category);
    }
}