package net.dillon.dillonlib.platform;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class FabricModPlatform extends ModPlatform {

    @Override
    public @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Main");
    }

    @Override
    public String modId() {
        return "dillonlib";
    }

    @Override
    public String version() {
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
        return PlatformRelease.BETA;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.PATCH;
    }

    @Override
    public @NotNull Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}