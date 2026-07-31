package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.factory.item.TotemFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Shadow
    private static ItemStack findTotem(Player player) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    /**
     * Redirects finding of totems to check if the totem is a totem factory.
     */
    @Redirect(method = "findTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean findTotemFactory(ItemStack stack, Item item) {
        return stack.is(Items.TOTEM_OF_UNDYING) || stack.getItem() instanceof TotemFactory;
    }

    /**
     * Redirects the totem particle logic so that custom particles can be used when using a {@link TotemFactory}.
     */
    @Redirect(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;createTrackingEmitter(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/particles/ParticleOptions;I)V"))
    private void totemFactoryParticles(ParticleEngine particleEngine, Entity entity, ParticleOptions particle, int lifeTime) {
        ParticleOptions realParticle = ParticleTypes.TOTEM_OF_UNDYING;
        int realLifeTime = 30;

        if (entity instanceof Player player) {
            ItemStack totem = findTotem(player);
            if (totem.getItem() instanceof TotemFactory totemFactory) {
                System.out.println("true");
                realParticle = totemFactory.getParticle();
                realLifeTime = totemFactory.getParticleLifeTime();
            }
        }
        Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, realParticle, realLifeTime);
    }
}