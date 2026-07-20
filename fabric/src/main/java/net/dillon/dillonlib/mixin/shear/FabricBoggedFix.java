package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Bogged.class)
public class FabricBoggedFix {

    /**
     * Registers {@link ShearsFactory} to work on bogged.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean mobInteractFactory(ItemStack stack, Object o) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}