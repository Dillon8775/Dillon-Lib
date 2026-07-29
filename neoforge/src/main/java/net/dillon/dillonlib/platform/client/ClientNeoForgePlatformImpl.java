package net.dillon.dillonlib.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;

public class ClientNeoForgePlatformImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    // For NeoForge, you must use RegisterKeyMappingsEvent manually to register the created keybind. This is done automatically for any keybind in NeoForgeClientEvents.
    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, String category, int value) {
        return new KeyMapping(name, value, category);
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}