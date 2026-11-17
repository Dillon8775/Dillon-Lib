package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.core.WritableRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Dill(env = DillEnv.CLIENT)
@Mixin(DebugScreenEntries.class)
public class DebugScreenEntriesMixin {

    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void create(WritableRegistry<DebugScreenEntry> registry, CallbackInfo ci) {
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> clientModPlatform.registerDebugEntries(registry));
    }
}