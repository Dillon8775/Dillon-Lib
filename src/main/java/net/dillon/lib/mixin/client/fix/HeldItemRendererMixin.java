package net.dillon.lib.mixin.client.fix;

import net.dillon.lib.util.ValidBowFactories;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes some incorrect rendering with custom bow and crossbow factories.
 */
@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean redirectHandRenderType(ItemStack stack, Item item, ClientPlayerEntity player) {
        return ValidBowFactories.isValidFactory(stack);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean redirectUsingItemHandRenderType(ItemStack stack, Item item) {
        return ValidBowFactories.isValidFactory(stack);
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean redirectRenderFirstPersonItem(ItemStack stack, Item item) {
        return ValidBowFactories.isValidCrossbow(stack);
    }
}