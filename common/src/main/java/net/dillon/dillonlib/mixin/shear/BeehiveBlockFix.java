package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(BeehiveBlock.class)
public class BeehiveBlockFix {

    /**
     * Registers {@link ShearsFactory} to work on beehive blocks.
     */
    @Redirect(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean useItemOnFactory(ItemStack stack, Item item) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}