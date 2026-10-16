package net.dillon.dillonlib.platform.mixinsafe;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;

public class MixinFabricPlatformImpl extends MixinModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FabricLoader.getInstance().isModLoaded(mod.id());
    }

    @Override
    public boolean isVersionPresent(ModReference mod) {
        if (!isModLoaded(mod)) {
            return false;
        }

        return FabricLoader.getInstance()
                .getModContainer(mod.id())
                .map(container -> {
                    if (mod.version().equals(ModReference.ANY)) {
                        return true;
                    }

                    try {
                        return VersionPredicate.parse(">=" + mod.version())
                                .test(
                                        Version.parse(
                                                Platforms.getCommonPlatform().commonModVersion(mod.id())
                                        )
                                );
                    } catch (VersionParsingException e) {
                        return false;
                    }
                })
                .orElse(false);
    }
}