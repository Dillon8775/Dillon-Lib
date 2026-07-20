package net.dillon.dillonlib.mixin.client.fix.bow;

import net.dillon.dillonlib.factory.item.BowFactory;
import net.dillon.dillonlib.factory.item.CrossbowFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes some incorrect rendering with bows and crossbows.
 */
@PredicateSigned
@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererFix {

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectHandRenderType(ItemStack stack, Object o) {
        return BowFactory.DEFAULT.test(stack) || CrossbowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectUsingItemHandRenderType(ItemStack stack, Object o) {
        return BowFactory.DEFAULT.test(stack) || CrossbowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectIsChargedCrossbow(ItemStack stack, Object o) {
        if (CrossbowFactory.DEFAULT.test(stack)) {
            return CrossbowItem.isCharged(stack) || CrossbowFactory.isCharged(stack);
        }
        return false;
    }
}