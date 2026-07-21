package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.info.ModReference;
import net.minecraftforge.fml.loading.FMLLoader;

public class MixinForgePlatformImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FMLLoader.getLoadingModList().getModFileById(mod.modId()) != null;
    }
}