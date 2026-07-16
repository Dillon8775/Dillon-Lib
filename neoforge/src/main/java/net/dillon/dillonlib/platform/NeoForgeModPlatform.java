package net.dillon.dillonlib.platform;

import net.minecraft.client.player.LocalPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class NeoForgeModPlatform extends ModPlatform {

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
        return ModList.get()
                .getModContainerById(this.modId())
                .map(c -> c.getModInfo().getVersion().toString().split("\\+", 2)[0])
                .orElse("unknown");
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.NEOFORGE;
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
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}