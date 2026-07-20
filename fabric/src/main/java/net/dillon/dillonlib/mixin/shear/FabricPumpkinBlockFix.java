package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PumpkinBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PumpkinBlock.class)
public class FabricPumpkinBlockFix {

    /**
     * Fixes {@code speedrunner shears} not working on pumpkin blocks.
     */
    @Redirect(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean useItemOnFactory(ItemStack stack, Object o) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}