package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(CopperGolem.class)
public class CopperGolemFix {

    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean mobInteractFactory(ItemStack stack, Object o) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}