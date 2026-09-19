package net.dillon.dillonlib.core;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModConfigLib;
import net.minecraft.network.chat.Component;

public class DillonLibModConfigLibs {
    public static final ModConfigLib YACL = ModConfigLib.of(Component.translatable("dillonlib.libs.yacl"), DillonLibModReferences.YACL);

    public static boolean isLibLoaded(ModConfigLib lib) {
        return Platforms.getDillonLibMixinPlatform().isModLoaded(lib.reference());
    }
}