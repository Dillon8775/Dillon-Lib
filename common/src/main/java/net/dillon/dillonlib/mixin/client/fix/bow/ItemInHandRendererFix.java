package net.dillon.dillonlib.mixin.client.fix.bow;

import net.dillon.dillonlib.factory.item.BowFactory;
import net.dillon.dillonlib.factory.item.CrossbowFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Fixes some incorrect rendering with bows and crossbows.
 */
@PredicateSigned
@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererFix {

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private static boolean redirectHandRenderType0(ItemStack stack, Item item) {
        return BowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private static boolean redirectHandRenderType1(ItemStack stack, Item item) {
        return BowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 2))
    private static boolean redirectHandRenderType2(ItemStack stack, Item item) {
        return CrossbowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 3))
    private static boolean redirectHandRenderType3(ItemStack stack, Item item) {
        return CrossbowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private static boolean redirectUsingItemHandRenderType0(ItemStack stack, Item item) {
        return BowFactory.DEFAULT.test(stack);
    }

    @Redirect(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private static boolean redirectUsingItemHandRenderType1(ItemStack stack, Item item) {
        return CrossbowFactory.DEFAULT.test(stack);
    }

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void redirectIsChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (CrossbowFactory.DEFAULT.test(stack)) {
            cir.setReturnValue(CrossbowItem.isCharged(stack) || CrossbowFactory.isCharged(stack));
        }
    }
}