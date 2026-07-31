package net.dillon.dillonlib.factory.item;

import net.dillon.dillonlib.mixin.entity.LivingEntityMixin;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DeathProtection;

/**
 * A default {@code Totem of undying}, with special attributes and built-in functionality to work correctly.
 * @since 1.0
 * @see LivingEntityMixin
 * @see net.minecraft.client.multiplayer.ClientPacketListener
 */
public class TotemFactory extends Item {
    public static final ItemFactoryPredicate THIS = stack -> stack.getItem() instanceof TotemFactory;
    public static final int DEFAULT_PARTICLE_LIFETIME = 30;
    private final DeathProtection deathProtectionComponent;
    private final ParticleOptions particle;
    private final int particleLifeTime;

    public TotemFactory(Properties properties, DeathProtection protection) {
        this(properties, protection, ParticleTypes.TOTEM_OF_UNDYING, DEFAULT_PARTICLE_LIFETIME);
    }

    public TotemFactory(Properties properties, DeathProtection protection, ParticleOptions particle, int particleLifeTime) {
        super(properties);
        this.deathProtectionComponent = protection;
        this.particle = particle;
        this.particleLifeTime = particleLifeTime;
    }

    /**
     * @return if the totem can be used. You can implement your custom logic by overriding this method.
     */
    public boolean canInvokeTotem(LivingEntity living, ItemStack totem, DamageSource source) {
        return true;
    }

    /**
     * Invokes the totem when the player attempts to die. You can implement your custom logic by overriding this method.
     */
    public void invokeTotem(LivingEntity living, ItemStack totem, DamageSource source) {
        this.deathProtectionComponent.applyEffects(totem, living);
        living.setHealth(1.0F);
        totem.shrink(1);
        living.level().broadcastEntityEvent(living, (byte)35); // byte for default totem client-side logic, see ClientPacketListener.handleEntityEvent for more
        if (living instanceof ServerPlayer player) {
            CriteriaTriggers.USED_TOTEM.trigger(player, new ItemStack(Items.TOTEM_OF_UNDYING)); // grants the "Postmortal" advancement to the player
        }
    }

    /**
     * @return the death protection component to be used for this factory.
     */
    public DeathProtection getDeathProtectionComponent() {
        return this.deathProtectionComponent;
    }

    /**
     * @return the particle to be used for this totem factory.
     */
    public ParticleOptions getParticle() {
        return this.particle;
    }

    /**
     * @return the particle lifetime to be used for this totem factory. This indicates how long (in ticks) the particles should display for when the totem is used.
     */
    public int getParticleLifeTime() {
        return this.particleLifeTime;
    }
}