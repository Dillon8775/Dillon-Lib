package net.dillonlib.mixin.main.fix;

import net.dillonlib.registry.DLR;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.TripwireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {

    /**
     * Fixes {@code shear factories} not working on tripwire hooks.
     */
    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectTripwireBlockOnBreakWithShears(ItemStack stack, Item item) {
        for (ShearsItem shears : DLR.getShearsFactories()) {
            if (stack.isOf(shears)) {
                return true;
            }
        }
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}