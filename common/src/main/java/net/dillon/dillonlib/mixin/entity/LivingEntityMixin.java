package net.dillon.dillonlib.mixin.entity;

import net.dillon.dillonlib.factory.item.TotemFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@PredicateSigned
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    /**
     * Calls the totem use event if supposed to and not totem of undying.
     */
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void checkTotemDeathProtectionFactory(DamageSource source, CallbackInfoReturnable<Boolean> cir, ItemStack stack, DeathProtection deathProtectionComponent) {
        if (stack.getItem() instanceof TotemFactory totemFactory) {
            totemFactory.invokeTotemUse((LivingEntity)(Object)this, stack, source);
            cir.setReturnValue(stack != null);
        }
    }
}