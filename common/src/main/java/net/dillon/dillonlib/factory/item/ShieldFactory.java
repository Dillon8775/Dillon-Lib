package net.dillon.dillonlib.factory.item;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.mixin.accessor.LivingEntityAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

/**
 * A default {@link ShieldItem}, which contains correct data for shields.
 * @since 1.0
 */
public class ShieldFactory extends ShieldItem {
    public static final ItemFactoryPredicate THIS = stack -> stack.getItem() instanceof ShieldFactory;

    public ShieldFactory(Properties properties, int disableTicks) {
        super(properties);
        Factories.SHIELDS.put(this, disableTicks);
    }

    /**
     * Uses the shield when hit. This can be over-ridden if you wish to have custom logic for your shield.
     * @see Player#hurtCurrentlyUsedShield(float)
     */
    public void hurtShieldFactory(Player player, float p_36383_) {
        if (!player.level().isClientSide) {
            player.awardStat(Stats.ITEM_USED.get(player.getUseItem().getItem()));
        }

        if (p_36383_ >= 3.0F) {
            int i = 1 + Mth.floor(p_36383_);
            InteractionHand interactionhand = player.getUsedItemHand();
            player.getUseItem().hurtAndBreak(i, player, (playerCopy) -> playerCopy.broadcastBreakEvent(interactionhand));
            if (player.getUseItem().isEmpty()) {
                if (interactionhand == InteractionHand.MAIN_HAND) {
                    player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                } else {
                    player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                }

                ((LivingEntityAccessor)player).setUseItem(ItemStack.EMPTY);
                player.playSound(SoundEvents.SHIELD_BREAK, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
            }
        }
    }
}