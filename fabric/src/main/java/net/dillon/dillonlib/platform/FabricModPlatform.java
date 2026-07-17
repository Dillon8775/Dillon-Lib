package net.dillon.dillonlib.platform;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class FabricModPlatform extends ModPlatform {

    @Override
    protected @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Fabric");
    }

    @Override
    protected String modId() {
        return "dillonlib";
    }

    @Override
    protected String modVersion() {
        return FabricLoader.getInstance()
                .getModContainer(this.modId())
                .map(c -> c.getMetadata().getVersion().getFriendlyString().split("\\+", 2)[0])
                .orElse("unknown");
    }

    @Override
    protected @NotNull PlatformName platformName() {
        return PlatformName.FABRIC;
    }

    @Override
    protected @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.STABLE;
    }

    @Override
    protected @NotNull LogoWidth logoWidth() {
        return LogoWidth.DEFAULT;
    }

    @Override
    protected @NotNull Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    protected boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}