package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.component.DebugBuilder;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Dill(env = DillEnv.CLIENT)
@Mixin(DebugScreenEntries.class)
public class DebugScreenEntriesMixin {
    @Shadow
    @Final
    @Mutable
    public static Map<DebugScreenProfile, Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus>> PROFILES;

    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void create(WritableRegistry<DebugScreenEntry> registry, CallbackInfo ci) {
        Platforms.forEachClientPlatform(clientModPlatform -> clientModPlatform.registerDebugEntries(registry));

        // Create a mutable copy of the default profiles
        Map<DebugScreenProfile, Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus>> newProfiles =
                new HashMap<>(DebugScreenEntries.PROFILES);

        // Create a mutable copy of the default profile entries
        Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> defaultProfile =
                new HashMap<>(newProfiles.get(DebugScreenProfile.DEFAULT));

        // Add custom debug entries
        defaultProfile.putAll(DebugBuilder.getCustomProfiles());

        // Replace the default profile
        newProfiles.put(DebugScreenProfile.DEFAULT, defaultProfile);

        PROFILES = newProfiles;
    }
}