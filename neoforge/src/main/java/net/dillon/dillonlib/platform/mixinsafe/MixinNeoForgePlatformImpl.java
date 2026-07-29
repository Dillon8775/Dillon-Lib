package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.ModReference;
import net.neoforged.fml.loading.FMLLoader;

public class MixinNeoForgePlatformImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FMLLoader.getCurrent().getLoadingModList().getModFileById(mod.modId()) != null;
    }
}