package net.dillon.dillonlib.core;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;

public class DillonLibModReferences {
    public static final ModReference QUALITY_OF_QUESO = new ModReference("qualityofqueso");
    public static final ModReference SPEEDRUNNER_MOD = new ModReference("speedrunnermod");
    public static final ModReference SIMPLE_KEYBINDS = new ModReference("simple_keybinds");
    public static final ModReference SURVIVAL_FLY = new ModReference("survival_fly");
    public static final ModReference YACL = new ModReference("yet_another_config_lib_v3");
    public static final ModReference SODIUM = new ModReference("sodium");

    public static boolean isModLoaded(ModReference reference) {
        return Platforms.getDillonLibMixinPlatform().isModLoaded(reference);
    }
}