package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.platform.ModReference;
import net.neoforged.fml.loading.FMLLoader;

public class MixinNeoForgeModPlatform extends MixinModPlatform {

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FMLLoader.getLoadingModList().getModFileById(mod.modId()) != null;
    }
}