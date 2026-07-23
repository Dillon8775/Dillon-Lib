package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class ForgeModImpl extends ModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return DillonLibMain.LOGGER;
    }

    @Override
    public String modVersion() {
        return ModList.get()
                .getModContainerById("dillonlib")
                .map(c -> c.getModInfo().getVersion().toString().split("[+-]", 2)[0])
                .orElse("unknown");
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.FORGE;
    }

    @Override
    public @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.STABLE;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.DEFAULT;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}