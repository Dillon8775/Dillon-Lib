package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class NeoForgePlatformImpl extends ModPlatform {

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
        return CommonPlatformGetter.get().commonModVersion(DillonLibMain.MOD_ID);
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.NEOFORGE;
    }

    @Override
    public @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.STABLE;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.DEFAULT;
    }
}