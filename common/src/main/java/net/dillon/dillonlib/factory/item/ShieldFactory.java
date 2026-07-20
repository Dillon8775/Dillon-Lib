package net.dillon.dillonlib.factory.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;

/**
 * A default {@link ShieldItem}, which contains correct data for shields.
 * @since 1.0
 */
public class ShieldFactory extends ShieldItem {
    public static final ItemFactoryPredicate THIS = stack -> stack.getItem() instanceof ShieldFactory;

    public ShieldFactory(Properties properties, float disableCooldownScale) {
        super(properties
                .stacksTo(1)
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                .component(
                        DataComponents.BLOCKS_ATTACKS,
                        new BlocksAttacks(
                                0.25F,
                                disableCooldownScale,
                                List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                Optional.of(SoundEvents.SHIELD_BREAK)
                ))
                .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
        );
    }
}