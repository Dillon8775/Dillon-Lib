package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForgeModImpl extends ModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Main");
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