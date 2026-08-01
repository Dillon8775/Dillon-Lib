package net.dillon.dillonlib.factory;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.data.BoatData;
import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.factory.item.SimpleItemGroupFactory;
import net.dillon.dillonlib.mixin.accessor.EntityTypesInvoker;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.dispenser.FlintAndSteelDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.material.Fluid;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Stores and registers different factories for specific Minecraft registries.
 * <p>See {@link net.dillon.dillonlib.factory.item} for more item-specific factories.</p>
 * @since 1.0
 * @see net.dillon.dillonlib.mixin
 */
public class Factories {
    public static final Set<BoatData> BOATS = new HashSet<>();

    /**
     * Registers a {@code boat entity factory} into the game.
     * @param id the {@link Identifier} for the boat entity
     * @param chest determines if the boat is a {@code chest boat}
     * @param dropItem the item that is dropped from the boat, and also registers dispenser behavior.
     * @return the registered boat entity type
     */
    public static <T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, Supplier<Item> dropItem, boolean chest) {
        return chest ?
                registerBoatFactory(id, EntityTypesInvoker.registerModChestBoatFactory(dropItem), true) :
                registerBoatFactory(id, EntityTypesInvoker.registerModBoatFactory(dropItem), false);
    }

    /**
     * Registers a custom-factory boat entity into the game.
     * @param factory the custom factory for the boat.
     * @return the custom-factory registered boat entity.
     */
    public static <T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, EntityType.EntityFactory<T> factory, boolean chest) {
        EntityType<T> boat = registerEntityType(
                ResourceKey.create(Registries.ENTITY_TYPE, id),
                EntityType.Builder.of(factory, MobCategory.MISC)
                        .noLootTable()
                        .sized(1.375F, 0.5625F)
                        .eyeHeight(0.5625F)
                        .clientTrackingRange(10)
        );

        BoatData boatData = new BoatData(boat, id, chest);
        BOATS.add(boatData);
        DillonLibMain.LOGGER.debug("Registered {} boat factory {}", chest ? "chest" : "default", id);
        return boat;
    }

    /**
     * Safely registers {@link BoatDispenseItemBehavior} for a list of boats and boat items.
     * @param boats a map of items and entity types to register your dispenser behavior. This should be called after both your entity types and items are initialized.
     */
    public static void registerBoatDispenserBehavior(List<Map<Item, EntityType<? extends AbstractBoat>>> boats) {
        for (Map<Item, EntityType<? extends AbstractBoat>> map : boats) {
            for (Map.Entry<Item, EntityType<? extends AbstractBoat>> entry : map.entrySet()) {
                Item item = entry.getKey();
                EntityType<? extends AbstractBoat> boatType = entry.getValue();
                DispenserBlock.registerBehavior(item, new BoatDispenseItemBehavior(boatType));
            }
        }
    }

    /**
     * Safely registers {@link net.minecraft.core.dispenser.ShearsDispenseItemBehavior} for a list of {@link ShearsFactory}s.
     * @param shears a list of ShearFactories to be registered for dispenser behavior.
     */
    public static void registerShearDispenserBehavior(List<Item> shears) {
        for (Item shear : shears) {
            if (!(shear instanceof ShearsFactory)) {
                throw new IllegalStateException("Item must be a ShearsFactory to register dispenser behavior!");
            }
            DispenserBlock.registerBehavior(shear, new ShearsDispenseItemBehavior());
        }
    }

    /**
     * Safely registers {@link FlintAndSteelDispenseItemBehavior} for a list of {@link IgnitableFactory.FlintAndSteel}s.
     * @param flintAndSteels a list of IgnitableFactories to be registered for dispenser behavior.
     */
    public static void registerFlintAndSteelDispenserBehavior(List<Item> flintAndSteels) {
        for (Item flintAndSteel : flintAndSteels) {
            if (!(flintAndSteel instanceof IgnitableFactory.FlintAndSteel)) {
                throw new IllegalStateException("Item must be a IgnitableFactory.FlintAndSteel to register dispenser behavior!");
            }
            DispenserBlock.registerBehavior(flintAndSteel, new FlintAndSteelDispenseItemBehavior());
        }
    }

    /**
     * Helper method for registering entity types.
     */
    public static <T extends Entity> EntityType<T> registerEntityType(ResourceKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }

    /**
     * Creates a simple item group with basic items. Should be called in your mod's common initialization stage. This will register your item group on all platforms automatically (fabric x (neo)forge).
     * @param id the {@link Identifier} for your item group (used to create the translation key)
     * @param icon the icon for your item group
     * @param entries the list of {@link ItemLike}s to be in your item group
     */
    public static void registerSimpleItemGroupFactory(Identifier id, ItemLike icon, List<ItemLike> entries) {
        registerSimpleItemGroupFactory(id, icon, () ->
                entries.stream()
                        .map(itemLike -> itemLike.asItem().getDefaultInstance())
                        .toList()
        );
    }

    /**
     * Creates a simple item group with a custom supplier for your items. Should be called in your mod's common initialization stage. This will register your item group on all platforms automatically (fabric x (neo)forge).
     * @param id the {@link Identifier} for your item group (used to create the translation key)
     * @param icon the icon for your item group
     * @param entries the list of items to be in your item group
     */
    public static void registerSimpleItemGroupFactory(Identifier id, ItemLike icon, Supplier<List<ItemStack>> entries) {
        Platforms.getCommonPlatform().registerItemGroup(new SimpleItemGroupFactory(id, icon, entries));
        DillonLibMain.LOGGER.debug("Registered item group factory {}", id);
    }

    /**
     * Adds an {@link ItemStack} to a specific {@link CreativeModeTab}. Should be called in your mod's initialization stage.
     * @param tab the tab you want to modify
     * @param item the item you want to add to the tab (as an item stack)
     */
    public static void factorItemStackIntoCreativeTab(ResourceKey<CreativeModeTab> tab, Supplier<ItemStack> item) {
        Platforms.getCommonPlatform().addItemToGroup(tab, item);
    }

    /**
     * Adds a {@link ItemLike} to a specific {@link CreativeModeTab}. Should be called in your mod's initialization stage.
     * @param tab the tab you want to modify
     * @param itemLike the item you want to add to the tab (as an item like)
     */
    public static void factorItemLikeIntoCreativeTab(ResourceKey<CreativeModeTab> tab, ItemLike itemLike) {
        factorItemStackIntoCreativeTab(tab, () -> itemLike.asItem().getDefaultInstance());
    }

    /**
     * Adds a {@link List} of {@link ItemLike}s to a specific {@link CreativeModeTab}. Should be called in your mod's initialization stage.
     * @param tab the tab you want to modify
     * @param items the list of item likes that you want to add
     */
    public static void factorItemLikesIntoCreativeTab(ResourceKey<CreativeModeTab> tab, List<ItemLike> items) {
        for (ItemLike itemLike : items) {
            factorItemLikeIntoCreativeTab(tab, itemLike);
        }
    }

    /**
     * Adds a {@link List} of {@link ItemStack}s to a specific {@link CreativeModeTab}. Should be called in your mod's initialization stage.
     * @param tab the tab you want to modify
     * @param items the list of item stacks that you want to add
     */
    public static void factorItemStacksIntoCreativeTab(ResourceKey<CreativeModeTab> tab, Supplier<List<ItemStack>> items) {
        for (ItemStack stack : items.get()) {
            factorItemStackIntoCreativeTab(tab, () -> stack);
        }
    }

    /**
     * Adds a {@link List} of {@link ItemLike}s to multiple {@link CreativeModeTab}s. Should be called in your mod's initialization stage.
     * @param tabs the list of tabs that you want to modify
     * @param items the list of item likes that you want to add to each tab
     */
    public static void factorItemLikesIntoCreativeTabs(List<ResourceKey<CreativeModeTab>> tabs, List<ItemLike> items) {
        for (ItemLike itemLike : items) {
            for (ResourceKey<CreativeModeTab> tab : tabs) {
                factorItemLikeIntoCreativeTab(tab, itemLike);
            }
        }
    }

    /**
     * Adds a {@link List} of {@link ItemStack}s to multiple {@link CreativeModeTab}s. Should be called in your mod's initialization stage.
     * @param tabs the list of tabs that you want to modify
     * @param items the list of item stacks that you want to add to each tab
     */
    public static void factorItemStacksIntoCreativeTabs(List<ResourceKey<CreativeModeTab>> tabs, Supplier<List<ItemStack>> items) {
        for (ItemStack stack : items.get()) {
            for (ResourceKey<CreativeModeTab> tab : tabs) {
                factorItemStackIntoCreativeTab(tab, () -> stack);
            }
        }
    }

    /**
     * Registers a {@code sound event}.
     */
    public static SoundEvent registerSoundEvent(Identifier id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    /**
     * Registers a {@code block} tag.
     */
    public static TagKey<Block> createBlockTag(Identifier id) {
        return createRegisterableTag(Registries.BLOCK, id);
    }

    /**
     * Registers a {@link BlockItemTagId} tag.
     */
    public static BlockItemTagId createBlockAndItemTag(Identifier id) {
        return BlockItemTagId.create(id, id);
    }

    /**
     * Registers a {@code item} tag.
     */
    public static TagKey<Item> createItemTag(Identifier id) {
        return createRegisterableTag(Registries.ITEM, id);
    }

    /**
     * Registers a {@code potion} tag.
     */
    public static TagKey<Potion> createPotionTag(Identifier id) {
        return createRegisterableTag(Registries.POTION, id);
    }

    /**
     * Registers a {@code enchantment} tag.
     */
    public static TagKey<Enchantment> createEnchantmentTag(Identifier id) {
        return createRegisterableTag(Registries.ENCHANTMENT, id);
    }

    /**
     * Registers a {@code fluid} tag.
     */
    public static TagKey<Fluid> createFluidTag(Identifier id) {
        return createRegisterableTag(Registries.FLUID, id);
    }

    /**
     * Registers a {@code structure} tag.
     */
    public static TagKey<Structure> createStructureTag(Identifier id) {
        return createRegisterableTag(Registries.STRUCTURE, id);
    }

    /**
     * Registers a {@code structure set} tag.
     */
    public static TagKey<StructureSet> createStructureSetTag(Identifier id) {
        return createRegisterableTag(Registries.STRUCTURE_SET, id);
    }

    /**
     * Registers a {@code template pool} tag.
     */
    public static TagKey<StructureTemplatePool> createTemplatePoolTag(Identifier id) {
        return createRegisterableTag(Registries.TEMPLATE_POOL, id);
    }

    /**
     * Registers a {@code configured feature} tag.
     */
    public static TagKey<ConfiguredFeature<?, ?>> createConfiguredFeatureTag(Identifier id) {
        return createRegisterableTag(Registries.CONFIGURED_FEATURE, id);
    }

    /**
     * Registers a {@code placed feature} tag.
     */
    public static TagKey<PlacedFeature> createPlacedFeatureTag(Identifier id) {
        return createRegisterableTag(Registries.PLACED_FEATURE, id);
    }

    /**
     * Registers a {@code biome} tag.
     */
    public static TagKey<Biome> createBiomeTag(Identifier id) {
        return createRegisterableTag(Registries.BIOME, id);
    }

    /**
     * Registers a {@code dimension type} tag.
     */
    public static TagKey<DimensionType> createDimensionTypeTag(Identifier id) {
        return createRegisterableTag(Registries.DIMENSION_TYPE, id);
    }

    /**
     * Registers a {@code dimension} tag.
     */
    public static TagKey<Level> createDimensionTag(Identifier id) {
        return createRegisterableTag(Registries.DIMENSION, id);
    }

    /**
     * Registers a {@code villager trade} tag.
     */
    public static TagKey<VillagerTrade> createVillagerTradeTag(Identifier id) {
        return createRegisterableTag(Registries.VILLAGER_TRADE, id);
    }

    /**
     * Registers a {@code trade set} tag.
     */
    public static TagKey<TradeSet> createTradeSetTag(Identifier id) {
        return createRegisterableTag(Registries.TRADE_SET, id);
    }

    /**
     * Registers a {@code attribute} tag.
     */
    public static TagKey<Attribute> createAttributeTag(Identifier id) {
        return createRegisterableTag(Registries.ATTRIBUTE, id);
    }

    /**
     * Registers a {@code damage type} tag.
     */
    public static TagKey<DamageType> createDamageTypeTag(Identifier id) {
        return createRegisterableTag(Registries.DAMAGE_TYPE, id);
    }

    /**
     * Registers a {@code entity type} tag.
     */
    public static TagKey<EntityType<?>> createEntityTypeTag(Identifier id) {
        return createRegisterableTag(Registries.ENTITY_TYPE, id);
    }

    /**
     * Registers any {@link TagKey} object of {@link Registries}.
     */
    public static <T> TagKey<T> createRegisterableTag(ResourceKey<Registry<T>> registerable, Identifier id) {
        return TagKey.create(registerable, id);
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}