package net.dillon.dillonlib.mixin.entity;

import net.dillon.dillonlib.factory.item.TotemFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@PredicateSigned
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    /**
     * Makes {@link TotemFactory}s work correctly.
     */
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void checkTotemDeathProtectionFactory(DamageSource source, CallbackInfoReturnable<Boolean> cir, ItemStack nullStack, DeathProtection protection) {
        LivingEntity self = (LivingEntity) (Object)this;
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack totem = this.getItemInHand(hand);
            if (totem.getItem() instanceof TotemFactory totemFactory && totemFactory.canInvokeTotem(self, totem, source)) {
                if (self instanceof ServerPlayer serverPlayer) {
                    serverPlayer.awardStat(Stats.ITEM_USED.get(totem.getItem()));
                    CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, totem);
                    totem.causeUseVibration(serverPlayer, GameEvent.ITEM_INTERACT_FINISH);
                }

                totemFactory.invokeTotem(self, totem, source);
                cir.setReturnValue(totem != null);
                break;
            }
        }
    }
}