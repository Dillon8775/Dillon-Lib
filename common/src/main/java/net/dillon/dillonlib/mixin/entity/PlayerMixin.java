package net.dillon.dillonlib.mixin.entity;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.factory.item.ShieldFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    public abstract ItemCooldowns getCooldowns();

    /**
     * Disables all {@link ShieldFactory} when hit with their respective cooldowns.
     */
    @Inject(method = "disableShield", at = @At("HEAD"))
    private void disableShieldFactories(CallbackInfo ci) {
        for (Map.Entry<ShieldFactory, Integer> entry : Factories.SHIELDS.entrySet()) {
            this.getCooldowns().addCooldown(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Hurts any current {@link ShieldFactory}, and applies custom shield logic for each shield factory.
     */
    @Inject(method = "hurtCurrentlyUsedShield", at = @At("HEAD"), cancellable = true)
    private void hurtCurrentShieldFactory(float p_36383_, CallbackInfo ci) {
        for (Map.Entry<ShieldFactory, Integer> entry : Factories.SHIELDS.entrySet()) {
            Player player = (Player)(Object)this;
            ShieldFactory factory = entry.getKey();
            if (player.getUseItem().getItem() instanceof ShieldFactory) {
                factory.hurtShieldFactory(player, p_36383_);
                ci.cancel(); // Cancel out original shield logic
            }
        }
    }
}