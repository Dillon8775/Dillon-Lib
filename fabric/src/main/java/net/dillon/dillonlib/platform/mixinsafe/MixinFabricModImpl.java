package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.ModReference;
import net.fabricmc.loader.api.FabricLoader;

public class MixinFabricModImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FabricLoader.getInstance().isModLoaded(mod.modId());
    }
}