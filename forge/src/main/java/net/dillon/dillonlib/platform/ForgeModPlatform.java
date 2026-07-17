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
    protected @NotNull Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Main");
    }

    @Override
    protected String modId() {
        return "dillonlib";
    }

    @Override
    protected String modVersion() {
        return ModList.get()
                .getModContainerById("dillonlib")
                .map(c -> c.getModInfo().getVersion().toString().split("[+-]", 2)[0])
                .orElse("unknown");
    }

    @Override
    protected @NotNull PlatformName platformName() {
        return PlatformName.FORGE;
    }

    @Override
    protected @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.BETA;
    }

    @Override
    protected @NotNull LogoWidth logoWidth() {
        return LogoWidth.PATCH;
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