package net.dillon.dillonlib.mixin.ignitable;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.CandleCakeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(CandleCakeBlock.class)
public class CandleCakeBlockFix {

    /**
     * Allows {@link IgnitableFactory}s to work on candles.
     */
    @Redirect(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean useItemOnFactory(ItemStack stack, Object o) {
        return stack.is(Items.FLINT_AND_STEEL) || IgnitableFactory.THIS_OR_STEEL.test(stack);
    }
}