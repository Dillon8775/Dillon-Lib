package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LeashFenceKnotEntity.class)
public class FabricLeashFenceKnotEntityFix {

    /**
     * Registers {@link ShearsFactory} to work on leash fence knots.
     */
    @Redirect(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean interactFactory(ItemStack stack, Object o) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}