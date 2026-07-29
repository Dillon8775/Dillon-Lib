package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Consumer;

@PredicateSigned
@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Makes all keybinding factories run and work.
     */
    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    private void implementKeyBindingFactories(CallbackInfo info) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            while (keyMapping.consumeClick()) {
                for (Map.Entry<KeyMapping, Consumer<LocalPlayer>> consumer : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.entrySet()) {
                    consumer.getValue().accept(Minecraft.getInstance().player);
                }
            }
        }
    }
}