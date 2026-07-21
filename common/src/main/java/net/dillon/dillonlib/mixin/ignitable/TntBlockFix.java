package net.dillon.dillonlib.mixin.ignitable;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.TntBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(TntBlock.class)
public class TntBlockFix {

    /**
     * Fixes {@code ignitables} not working on TNT blocks.
     */
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean redirectTntOnUseWithItem(ItemStack stack, Item item) {
        return stack.is(Items.FLINT_AND_STEEL) || IgnitableFactory.THIS_OR_STEEL.test(stack);
    }
}