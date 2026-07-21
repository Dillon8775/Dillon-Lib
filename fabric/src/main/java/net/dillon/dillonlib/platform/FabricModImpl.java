package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricModImpl extends ModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Fabric");
    }

    @Override
    public String modVersion() {
        return FabricLoader.getInstance()
                .getModContainer(this.modId())
                .map(c -> c.getMetadata().getVersion().getFriendlyString().split("\\+", 2)[0])
                .orElse("unknown");
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.FABRIC;
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