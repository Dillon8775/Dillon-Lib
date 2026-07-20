package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(MushroomCow.class)
public class MushroomCowFix {

    /**
     * Registers {@link ShearsFactory} to work with shearing mushrooms.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private boolean mobInteractFactory(ItemStack stack, Item item) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}