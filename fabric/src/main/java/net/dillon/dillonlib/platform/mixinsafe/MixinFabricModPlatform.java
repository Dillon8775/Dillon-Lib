package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.platform.ModReference;
import net.fabricmc.loader.api.FabricLoader;

public class MixinFabricModPlatform extends MixinModPlatform {

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FabricLoader.getInstance().isModLoaded(mod.modId());
    }
}