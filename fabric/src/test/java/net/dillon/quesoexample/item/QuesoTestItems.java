package net.dillon.quesoexample.item;

import net.dillon.dillonlib.factory.item.*;
import net.dillon.dillonlib.mixin.accessor.ItemsInvoker;
import net.dillon.quesoexample.entity.QuesoEntityTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;

public class QuesoTestItems {
    public static final Item QUESO_SHIELD = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_SHIELD, properties -> new ShieldFactory(properties
            .rarity(Rarity.EPIC)
            .durability(500), 0.8F));
    public static final Item QUESO_BOW = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_BOW, BowFactory::new, new Item.Properties()
            .rarity(Rarity.RARE)
    );
    public static final Item QUESO_CROSSBOW = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_CROSSBOW, CrossbowFactory::new, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
    );
    public static final Item QUESO_SHEARS = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_SHEARS, ShearsFactory::new, new Item.Properties()
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
            .rarity(Rarity.UNCOMMON)
            .durability(150)
    );
    public static final Item QUESO_FIRE = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_FIRE, IgnitableFactory::new, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
    );
    public static final Item QUESO_FLINT_AND_STEEL = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_FLINT_AND_STEEL, IgnitableFactory.FlintAndSteel::new, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
            .durability(200)
    );
    public static final Item QUESO_BOAT = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_BOAT, properties -> new BoatItem(QuesoEntityTypes.QUESO_BOAT, properties));
    public static final Item QUESO_CHEST_BOAT = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_CHEST_BOAT, properties -> new BoatItem(QuesoEntityTypes.QUESO_CHEST_BOAT, properties));

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}