package net.dillon.quesoexample.item;

import net.dillon.dillonlib.factory.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;

public class QuesoTestItems {
    public static final Item QUESO_SHIELD = Items.registerItem(QuesoItemIds.QUESO_SHIELD, new ShieldFactory(new Item.Properties()
            .rarity(Rarity.EPIC)
            .durability(500), 80));
    public static final Item QUESO_BOW = Items.registerItem(QuesoItemIds.QUESO_BOW, new BowFactory(new Item.Properties()
            .rarity(Rarity.RARE)));
    public static final Item QUESO_CROSSBOW = Items.registerItem(QuesoItemIds.QUESO_CROSSBOW, new CrossbowFactory(new Item.Properties()
            .rarity(Rarity.UNCOMMON)));
    public static final Item QUESO_SHEARS = Items.registerItem(QuesoItemIds.QUESO_SHEARS, new ShearsFactory(new Item.Properties()
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
            .rarity(Rarity.UNCOMMON)
            .durability(150)));
    public static final Item QUESO_FIRE = Items.registerItem(QuesoItemIds.QUESO_FIRE, new IgnitableFactory(new Item.Properties()
            .rarity(Rarity.UNCOMMON)));
    public static final Item QUESO_FLINT_AND_STEEL = Items.registerItem(QuesoItemIds.QUESO_FLINT_AND_STEEL, new IgnitableFactory.FlintAndSteel(new Item.Properties()
            .rarity(Rarity.UNCOMMON)
            .durability(200)));
    public static final Item QUESO_TOTEM = Items.registerItem(QuesoItemIds.QUESO_TOTEM, new TotemFactory(new Item.Properties(), ParticleTypes.ANGRY_VILLAGER, 30));

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}