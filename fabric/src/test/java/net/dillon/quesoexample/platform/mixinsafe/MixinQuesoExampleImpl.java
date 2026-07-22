package net.dillon.quesoexample.platform.mixinsafe;

import net.dillon.dillonlib.platform.info.ModReference;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.quesoexample.QuesoExampleMod;
import net.fabricmc.loader.api.FabricLoader;

public class MixinQuesoExampleImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FabricLoader.getInstance().isModLoaded(mod.modId());
    }

    @Override
    public boolean shouldApplyFactories() {
        return true;
    }

    @Override
    public boolean shouldApplyFullBright() {
        return true;
    }
}