package net.dillon.lib.mixin.main.player;

import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * A mixin responsible for handling different shield actions.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Allows shields to take a hit.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeNewShieldHit(ServerWorld world, LivingEntity attacker, CallbackInfo ci) {
        for (ShieldItem shield : DillonsRegistry.getShieldFactories().keySet()) {
            this.getItemCooldownManager().set(shield.getDefaultStack(), DillonsRegistry.getShieldFactories().get(shield));
        }
    }
}