package net.dillon.dillonlib.platform.client;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraft.client.player.LocalPlayer;

@Dill(env = DillEnv.CLIENT)
public class ClientNeoForgePlatformImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}