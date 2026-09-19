package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.Platform;
import net.dillon.dillonlib.platform.info.Release;
import org.jetbrains.annotations.NotNull;

public class NeoForgePlatformImpl extends ModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public @NotNull Release release() {
        return Release.STABLE;
    }

    @Override
    public String modVersion() {
        return Platforms.getCommonPlatform().commonModVersion(DillonLibMain.MOD_ID);
    }

    @Override
    public @NotNull Platform platform() {
        return Platform.NEOFORGE;
    }
}