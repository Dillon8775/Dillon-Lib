package net.dillon.dillonlib.core;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;

public class DillonLibModReferences {
    public static final ModReference QUALITY_OF_QUESO = ModReference.of("qualityofqueso");
    public static final ModReference SPEEDRUNNER_MOD = ModReference.of("speedrunnermod");
    public static final ModReference SIMPLE_KEYBINDS = ModReference.of("simple_keybinds");
    public static final ModReference SURVIVAL_FLY = ModReference.of("survival_fly");
    public static final ModReference YACL = ModReference.of("yet_another_config_lib_v3");
    public static final ModReference SODIUM = ModReference.of("sodium");

    public static boolean isModLoaded(ModReference reference) {
        return Platforms.getDillonLibMixinPlatform().isModLoaded(reference);
    }
}