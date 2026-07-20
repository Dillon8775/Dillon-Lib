package net.dillon.quesoinstance.platform.mixinsafe;

import net.dillon.dillonlib.platform.info.ModReference;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.quesoinstance.QuesoInstance;
import net.neoforged.fml.loading.FMLLoader;

public class MixinQuesoInstanceImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return QuesoInstance.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FMLLoader.getCurrent().getLoadingModList().getModFileById(mod.modId()) != null;
    }
}