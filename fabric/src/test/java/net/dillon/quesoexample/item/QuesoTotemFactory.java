package net.dillon.quesoexample.item;

import net.dillon.dillonlib.factory.item.TotemFactory;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;

public class QuesoTotemFactory extends TotemFactory {

    public QuesoTotemFactory(Properties properties, DeathProtection protection, ParticleOptions particle, int particleLifeTime) {
        super(properties, protection, particle, particleLifeTime);
    }

    public void invokeTotem(LivingEntity living, ItemStack totem, DamageSource source) {
        super.invokeTotem(living, totem, source);
    }
}