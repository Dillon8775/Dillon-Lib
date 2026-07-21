package net.dillon.dillonlib.mixin.entity;

import net.dillon.dillonlib.factory.item.TotemFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@PredicateSigned
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    /**
     * Calls the totem use event if supposed to and not totem of undying.
     */
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "HEAD"), cancellable = true)
    private void checkTotemDeathProtectionFactory(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = null;

        for (InteractionHand interactionhand : InteractionHand.values()) {
            ItemStack itemstack1 = this.getItemInHand(interactionhand);
            if (itemstack1.getItem() instanceof TotemFactory) {
                itemstack = itemstack1.copy();
                itemstack1.shrink(1);
                break;
            }
        }

        if (itemstack != null && itemstack.getItem() instanceof TotemFactory totemFactory) {
            totemFactory.invokeTotemUse((LivingEntity)(Object)this, source);
        }

        cir.setReturnValue(itemstack != null);
    }
}