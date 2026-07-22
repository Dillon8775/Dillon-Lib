package net.dillon.dillonlib.factory;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.factory.item.ShieldFactory;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores different factories for Minecraft items and blocks, to register most of the things needed for that special item with one single method.
 * <p>See {@link net.dillon.dillonlib.factory.item} for more item-specific factories.</p>
 * @since 1.0
 * @see net.dillon.dillonlib.mixin
 */
public class Factories {
    public static final Set<ShearsFactory> SHEARS = new HashSet<>();
    public static final Set<IgnitableFactory.FlintAndSteel> FLINT_AND_STEELS = new HashSet<>();
    public static final Map<ShieldFactory, Integer> SHIELDS = new HashMap<>();

    /**
     * Helper method for registering boat types.
     */
    public static <T extends Entity> EntityType<T> registerEntityType(ResourceKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key.location().toString()));
    }

    /**
     * Registers a {@code sound event}.
     */
    public static SoundEvent registerSoundEvent(ResourceLocation id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    /**
     * Registers a {@code block} tag.
     */
    public static TagKey<Block> createBlockTag(ResourceLocation id) {
        return createRegisterableTag(Registries.BLOCK, id);
    }

    /**
     * Registers a {@code item} tag.
     */
    public static TagKey<Item> createItemTag(ResourceLocation id) {
        return createRegisterableTag(Registries.ITEM, id);
    }

    /**
     * Registers a {@code potion} tag.
     */
    public static TagKey<Potion> createPotionTag(ResourceLocation id) {
        return createRegisterableTag(Registries.POTION, id);
    }

    /**
     * Registers a {@code enchantment} tag.
     */
    public static TagKey<Enchantment> createEnchantmentTag(ResourceLocation id) {
        return createRegisterableTag(Registries.ENCHANTMENT, id);
    }

    /**
     * Registers a {@code fluid} tag.
     */
    public static TagKey<Fluid> createFluidTag(ResourceLocation id) {
        return createRegisterableTag(Registries.FLUID, id);
    }

    /**
     * Registers a {@code structure} tag.
     */
    public static TagKey<Structure> createStructureTag(ResourceLocation id) {
        return createRegisterableTag(Registries.STRUCTURE, id);
    }

    /**
     * Registers a {@code structure set} tag.
     */
    public static TagKey<StructureSet> createStructureSetTag(ResourceLocation id) {
        return createRegisterableTag(Registries.STRUCTURE_SET, id);
    }

    /**
     * Registers a {@code template pool} tag.
     */
    public static TagKey<StructureTemplatePool> createTemplatePoolTag(ResourceLocation id) {
        return createRegisterableTag(Registries.TEMPLATE_POOL, id);
    }

    /**
     * Registers a {@code configured feature} tag.
     */
    public static TagKey<ConfiguredFeature<?, ?>> createConfiguredFeatureTag(ResourceLocation id) {
        return createRegisterableTag(Registries.CONFIGURED_FEATURE, id);
    }

    /**
     * Registers a {@code placed feature} tag.
     */
    public static TagKey<PlacedFeature> createPlacedFeatureTag(ResourceLocation id) {
        return createRegisterableTag(Registries.PLACED_FEATURE, id);
    }

    /**
     * Registers a {@code biome} tag.
     */
    public static TagKey<Biome> createBiomeTag(ResourceLocation id) {
        return createRegisterableTag(Registries.BIOME, id);
    }

    /**
     * Registers a {@code dimension type} tag.
     */
    public static TagKey<DimensionType> createDimensionTypeTag(ResourceLocation id) {
        return createRegisterableTag(Registries.DIMENSION_TYPE, id);
    }

    /**
     * Registers a {@code dimension} tag.
     */
    public static TagKey<Level> createDimensionTag(ResourceLocation id) {
        return createRegisterableTag(Registries.DIMENSION, id);
    }

    /**
     * Registers a {@code attribute} tag.
     */
    public static TagKey<Attribute> createAttributeTag(ResourceLocation id) {
        return createRegisterableTag(Registries.ATTRIBUTE, id);
    }

    /**
     * Registers a {@code damage type} tag.
     */
    public static TagKey<DamageType> createDamageTypeTag(ResourceLocation id) {
        return createRegisterableTag(Registries.DAMAGE_TYPE, id);
    }

    /**
     * Registers a {@code entity type} tag.
     */
    public static TagKey<EntityType<?>> createEntityTypeTag(ResourceLocation id) {
        return createRegisterableTag(Registries.ENTITY_TYPE, id);
    }

    /**
     * Registers any {@link TagKey} object of {@link Registries}.
     */
    public static <T> TagKey<T> createRegisterableTag(ResourceKey<Registry<T>> registerable, ResourceLocation id) {
        return TagKey.create(registerable, id);
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}