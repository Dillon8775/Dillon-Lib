package net.dillonlib.main;

import net.dillonlib.registry.DLR;
import net.dillonlib.registry.item.BowFactory;
import net.dillonlib.registry.item.CrossbowFactory;
import net.dillonlib.registry.sign.CustomSignBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DryVegetationBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static net.dillonlib.LibraryTest.id;

public class LibTest {
    public static final Item TEST_SHIELD = DLR.registerShieldFactory(id("test_shield"), ShieldItem::new, new Item.Settings(), 50);
    public static final Item TEST_BOW = DLR.registerBowFactory(id("test_bow"), settings -> new BowFactory(settings, 5.0F, 5.0F), new Item.Settings());
    public static final Item TEST_CROSSBOW = DLR.registerCrossbowFactory(id("test_crossbow"), settings -> new CrossbowFactory(settings, 2.0F, 2.0F, 1.0F), new Item.Settings());
    public static final Item TEST_SHEARS = DLR.registerShearsFactory(id("test_shears"), ShearsItem::new, ShearsItem.createToolComponent(), new Item.Settings());
    public static final EntityType<BoatEntity> TEST_BOAT_ENTITY = DLR.registerBoatFactory(id("test_boat_entity"), LibTest.TEST_BOAT, false);
    public static final Item TEST_BOAT = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_boat")), settings -> new BoatItem(
            LibTest.TEST_BOAT_ENTITY, settings), new Item.Settings());
    public static final Block TEST_SIGN = DLR.registerSignBlockFactory(id("test_sign_block"), settings -> new CustomSignBlock(id("test"), settings), AbstractBlock.Settings.create());
    public static final Block TEST_CUTOUT = DLR.registerCutoutBlock(id("test_cutout_block"), DryVegetationBlock::new, AbstractBlock.Settings.create());
    public static final Item TEST_SIGN_BLOCK = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_sign")), settings -> new BlockItem(LibTest.TEST_SIGN, settings), new Item.Settings());
    public static final Item TEST_CUTOUT_BLOCK = Items.register(RegistryKey.of(RegistryKeys.ITEM, id("test_cutout")), settings -> new BlockItem(LibTest.TEST_CUTOUT, settings), new Item.Settings());

    public static void init() {
        DillonLib.error("Initialized LibTest");
    }
}