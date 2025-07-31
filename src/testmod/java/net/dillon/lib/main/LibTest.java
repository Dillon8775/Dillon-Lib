package net.dillon.lib.main;

import net.dillon.lib.DillonLib;
import net.dillon.lib.registry.DillonsRegistry;
import net.dillon.lib.registry.item.BowFactory;
import net.dillon.lib.registry.item.CrossbowFactory;
import net.dillon.lib.registry.sign.CustomSignBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DryVegetationBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static net.dillon.lib.DillonLib.id;

public class LibTest {
    public static final Item TEST_SHIELD = DillonsRegistry.registerShieldFactory(id("test_shield"), ShieldItem::new, new Item.Settings(), 50);
    public static final Item TEST_BOW = DillonsRegistry.registerBowFactory(id("test_bow"), settings -> new BowFactory(settings, 5.0F, 5.0F), new Item.Settings());
    public static final Item TEST_CROSSBOW = DillonsRegistry.registerCrossbowFactory(id("test_crossbow"), settings -> new CrossbowFactory(settings, 2.0F, 2.0F, 1.0F), new Item.Settings());
    public static final Item TEST_SHEARS = DillonsRegistry.registerShearsFactory(id("test_shears"), ShearsItem::new, ShearsItem.createToolComponent(), new Item.Settings());
    public static final EntityType<BoatEntity> TEST_BOAT_ENTITY = DillonsRegistry.registerBoatFactory(id("test_boat_entity"), LibTest.TEST_BOAT, false);
    public static final Item TEST_BOAT = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_boat")), settings -> new BoatItem(
            LibTest.TEST_BOAT_ENTITY, settings), new Item.Settings());
    public static final Block TEST_SIGN = DillonsRegistry.registerSignBlockFactory(id("test_sign_block"), settings -> new CustomSignBlock(id("test"), settings), AbstractBlock.Settings.create());
    public static final Block TEST_CUTOUT = DillonsRegistry.registerCutoutBlock(id("test_cutout_block"), DryVegetationBlock::new, AbstractBlock.Settings.create());
    public static final Item TEST_SIGN_BLOCK = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_sign")), settings -> new BlockItem(LibTest.TEST_SIGN, settings), new Item.Settings());
    public static final Item TEST_CUTOUT_BLOCK = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_cutout")), settings -> new BlockItem(LibTest.TEST_CUTOUT, settings), new Item.Settings());

    public static void init() {
        DillonLib.error("Initialized LibTest");
    }
}