package net.dillonlib.mixin.client;

import net.dillonlib.registry.DLRC;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    /**
     * Implements all keybinding factory functions.
     */
    @Inject(method = "handleInputEvents", at = @At("TAIL"))
    private void implementKeyBindingFactories(CallbackInfo info) {
        for (KeyBinding keyBinding : DLRC.getKeyBindingFactories().keySet()) {
            while (keyBinding.wasPressed()) {
                for (Runnable runnable : DLRC.getKeyBindingFactories().get(keyBinding)) {
                    runnable.run();
                }
            }
        }
    }
}