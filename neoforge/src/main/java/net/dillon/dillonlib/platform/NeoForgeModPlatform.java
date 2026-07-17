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
    protected @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/NeoForge");
    }

    @Override
    protected String modId() {
        return "dillonlib";
    }

    @Override
    protected String modVersion() {
        return ModList.get()
                .getModContainerById(this.modId())
                .map(c -> c.getModInfo().getVersion().toString().split("\\+", 2)[0])
                .orElse("unknown");
    }

    @Override
    protected @NotNull PlatformName platformName() {
        return PlatformName.NEOFORGE;
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
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    protected boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }
}