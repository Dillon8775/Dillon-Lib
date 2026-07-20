package net.dillon.dillonlib.mixin.entity.fix;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(SulfurCube.class)
public class SulfurCubeFix {

    /**
     * Allows any {@link IgnitableFactory} to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean allowIgitables(ItemStack stack, Object o) {
        return stack.is(Items.FLINT_AND_STEEL) || IgnitableFactory.THIS_OR_STEEL.test(stack);
    }

    /**
     * Allows any {@link IgnitableFactory} to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 1))
    private boolean allowIgnitables(ItemStack stack, Object o) {
        return stack.is(Items.FIRE_CHARGE) || IgnitableFactory.THIS_OR_STEEL.test(stack);
    }

    /**
     * Allows any {@link IgnitableFactory} to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 2))
    private boolean allowSpeedrunnerFlintAndSteelToWork(ItemStack stack, Object o) {
        return stack.is(Items.FLINT_AND_STEEL) || IgnitableFactory.THIS_OR_STEEL.test(stack);
    }

    /**
     * Allows any {@link ShearsFactory} to work on sulfur cubes.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 3))
    private boolean allowSpeedrunnerShearsToWork(ItemStack stack, Object o) {
        return ShearsFactory.DEFAULT.test(stack);
    }
}