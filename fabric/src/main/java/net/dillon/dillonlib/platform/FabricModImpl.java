package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.Platform;
import net.dillon.dillonlib.platform.info.Release;

public class FabricModImpl extends ModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public String modVersion() {
        return Platforms.getCommonPlatform().commonModVersion(DillonLibMain.MOD_ID);
    }

    @Override
    public Release release() {
        return Release.STABLE;
    }

    @Override
    public Platform platform() {
        return Platform.FABRIC;
    }
}