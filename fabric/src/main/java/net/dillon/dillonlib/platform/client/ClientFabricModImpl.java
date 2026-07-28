package net.dillon.dillonlib.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.core.DillonLibMain;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;

public class ClientFabricModImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public KeyMapping createKeyMapping(String name, InputConstants.Type type, String category, int value) {
        return KeyBindingHelper.registerKeyBinding(new KeyMapping(name, value, category));
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}