package net.dillon.dillonlib.platform;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class ForgeModPlatform extends ModPlatform {

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