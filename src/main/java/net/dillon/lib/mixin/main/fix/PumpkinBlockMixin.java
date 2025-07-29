package net.dillon.lib.mixin.main.fix;

import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PumpkinBlock.class)
public class PumpkinBlockMixin {

    /**
     * Fixes {@code shear factories} not working on pumpkin blocks.
     */
    @Redirect(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectPumpkinBlockOnUseWithItem(ItemStack stack, Item item) {
        for (ShearsItem shears : DillonsRegistry.getShearsFactories()) {
            if (stack.isOf(shears)) {
                return true;
            }
        }
        return stack.isOf(Items.SHEARS);
    }
}