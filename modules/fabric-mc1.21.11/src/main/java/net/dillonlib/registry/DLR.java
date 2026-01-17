package net.dillonlib.registry;

import net.dillonlib.main.DillonLib;
import net.dillonlib.annotation.GlobalUse;
import net.dillonlib.annotation.PrivateUse;
import net.dillonlib.registry.data.BoatData;
import net.dillonlib.registry.data.ShieldData;
import net.dillonlib.registry.item.BowFactory;
import net.dillonlib.registry.item.CrossbowFactory;
import net.dillonlib.registry.sign.CustomSignBlock;
import net.dillonlib.registry.sign.hanging.CustomHangingSignBlock;
import net.dillonlib.registry.sign.hanging.wall.CustomWallHangingSignBlock;
import net.dillonlib.registry.sign.wall.CustomWallSignBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * A registry class, which registers certain items to a list to register in other parts of the game to behave correctly.
 */
@GlobalUse
public class DLR {
    private static final Set<ShieldData> SHIELD_FACTORIES = new HashSet<>();
    private static final Map<BowFactory, Float> BOW_FACTORIES = new HashMap<>();
    private static final Map<Identifier, WoodType> WOOD_TYPE_CACHE = new ConcurrentHashMap<>();
    private static final Set<CrossbowFactory> CROSSBOW_FACTORIES = new HashSet<>();
    private static final Set<ShearsItem> SHEARS_FACTORIES = new HashSet<>();
    private static final Set<Block> CUTOUT_BLOCKS = new HashSet<>();
    private static final Set<BoatData> BOATS = new HashSet<>();

    /**
     * Registers a {@code shield factory} into the game.
     * @param id the {@link Identifier} for the shield item
     * @param factory the item class type, in most cases you would use {@link ShieldItem}
     * @param settings the settings for the item
     * @param cooldown determines how long the shield gets disabled for when hit by an axe
     *
     * @return the registered shield
     */
    @GlobalUse
    public static ShieldItem registerShieldFactory(Identifier id, Function<Item.Settings, Item> factory, Item.Settings settings, int cooldown) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, id), factory, settings
                .maxCount(1)
                .component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                .component(
                        DataComponentTypes.BLOCKS_ATTACKS,
                        new BlocksAttacksComponent(
                                0.25F,
                                1.0F,
                                List.of(new BlocksAttacksComponent.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F),
                                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                                Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND));
        if (!(item instanceof ShieldItem shieldItem)) {
            throw new IllegalArgumentException("Item is not a shield item!");
        }
        SHIELD_FACTORIES.add(new ShieldData(id, shieldItem, cooldown));
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
            DLRC.SHIELD_IDENTIFIERS.add(new SpriteIdentifier[]{
                    new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id.getPath() + "_base")),
                    new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id.getPath() + "_base_no_pattern"))
            });
        }
        DillonLib.debug("Registered shield factory " + id + " with cooldown of " + cooldown + ".");
        return shieldItem;
    }

    /**
     * Registers a {@code shears factory} into the game.
     * @param id the {@link Identifier} for the shears item
     * @param factory the item class type
     * @param component the tool component and it's settings for when breaking certain blocks (see {@link ShearsItem#createToolComponent()}.
     * @param settings the settings for the item
     *
     * @return the registered shears
     */
    @GlobalUse
    public static ShearsItem registerShearsFactory(Identifier id, Function<Item.Settings, Item> factory, ToolComponent component, Item.Settings settings) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, id), factory, settings
                .component(DataComponentTypes.TOOL, component));
        if (!(item instanceof ShearsItem)) {
            throw new IllegalArgumentException("Item is not a shears item!");
        }
        SHEARS_FACTORIES.add((ShearsItem)item);
        DillonLib.debug("Registered shears factory " + id + ".");
        return (ShearsItem)item;
    }

    /**
     * Registers a {@code bow factory} into the game.
     * @param id the {@link Identifier} for the bow item
     * @param factory the item class type. In this case, use {@link BowFactory}
     * @param settings the settings for the bow item
     *
     * @return the registered bow
     *
     * @see BowFactory
     */
    @GlobalUse
    public static BowFactory registerBowFactory(Identifier id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, id), factory, settings);
        if (!(item instanceof BowFactory bowFactory)) {
            throw new IllegalArgumentException("Item is not a bow item factory!");
        }
        BOW_FACTORIES.put(bowFactory, bowFactory.getPullSpeed());
        return bowFactory;
    }

    /**
     * Registers a {@code crossbow factory} into the game.
     * @param id the {@link Identifier} for the crossbow item
     * @param factory the item class type. In this case, use {@link CrossbowFactory}
     * @param settings the settings for the crossbow item
     *
     * @return the registered crossbow
     *
     * @see CrossbowFactory
     */
    @GlobalUse
    public static CrossbowFactory registerCrossbowFactory(Identifier id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, id), factory, settings);
        if (!(item instanceof CrossbowFactory crossbowFactory)) {
            throw new IllegalArgumentException("Item is not a crossbow item factory!");
        }
        CROSSBOW_FACTORIES.add(crossbowFactory);
        DillonLib.debug("Registered crossbow factory " + id + ".");
        return crossbowFactory;
    }

    /**
     * Registers a {@code cutout} block, which has a special {@code BlockRenderLayerMap.}
     * @param id the {@link Identifier} for the block
     * @param factory the block class type
     * @param settings the settings for the cutout block
     *
     * @return the registered "cutout" block
     */
    @GlobalUse
    public static Block registerCutoutBlock(Identifier id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, id), factory, settings);
        CUTOUT_BLOCKS.add(block);
        DillonLib.debug("Registered cutout block " + id + ".");
        return block;
    }

    /**
     * Registers a {@code boat entity factory} into the game.
     * @param id the {@link Identifier} for the boat entity
     * @param boatItem the boat entity's respective {@link BoatItem} (the item which places the boat onto the ground/fluid).
     * @param chest determines if the boat is a {@code chest boat}
     * @return the registered boat entity type
     */
    @GlobalUse
    public static EntityType<BoatEntity> registerBoatFactory(Identifier id, Item boatItem, boolean chest) {
        EntityType<BoatEntity> boat = register(id,
                EntityType.Builder.create(EntityType.getBoatFactory(() -> boatItem), SpawnGroup.MISC)
                        .dropsNothing()
                        .dimensions(1.375F, 0.5625F)
                        .eyeHeight(0.5625F)
                        .maxTrackingRange(10));
        BOATS.add(new BoatData(boat, id, chest));
        DillonLib.debug("Registered boat factory " + id + ".");
        return boat;
    }

    /**
     * Registration helper for vanilla sign types (WallSignBlock, HangingSignBlock, etc.).  The sign block will be
     * registered to the block registry, and also as a valid block for the appropriate vanilla sign block entity.
     * This method creates the registry key and applies it to the block settings for you.
     * @param id The identifier of the sign block to be registered
     * @param factory A factory which creates the block to be registered using the provided block settings. In this case, you would need to use one of the {@code CustomSignBlock} classes to register the custom wood type correctly.
     * @return The registered sign block
     * @param <T> A descendant of {@linkplain AbstractSignBlock}
     *
     * @see CustomSignBlock
     * @see CustomWallSignBlock
     * @see CustomHangingSignBlock
     * @see CustomWallHangingSignBlock
     */
    @GlobalUse
    public static <T extends AbstractSignBlock> T registerSignBlockFactory(Identifier id, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);

        DillonLib.debug("Registering sign block " + id + " with factory " + factory.getClass().getName() + ".");
        return registerSignBlock(key, factory.apply(settings.registryKey(key)));
    }

    /**
     * Registration helper for vanilla sign types (WallSignBlock, HangingSignBlock, etc.).  The sign block will be
     * registered to the block registry, and also as a valid block for the appropriate vanilla sign block entity.
     * This method requires the block settings applied to the block already had the registry key applied.
     * @param key The registery key of the sign block to be registered
     * @param block The sign block to be registered
     * @return The registered sign block
     * @param <T> A descendant of {@linkplain AbstractSignBlock}
     */
    @PrivateUse
    private static <T extends AbstractSignBlock> T registerSignBlock(RegistryKey<Block> key, T block) {
        if (block instanceof SignBlock || block instanceof WallSignBlock) {
            BlockEntityType.SIGN.addSupportedBlock(block);
        } else if (block instanceof HangingSignBlock || block instanceof WallHangingSignBlock) {
            BlockEntityType.HANGING_SIGN.addSupportedBlock(block);
        } else {
            throw new IllegalArgumentException("This method only accepts vanilla sign blocks and descendants!");
        }

        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Creates and registers a {@linkplain WoodType} and associated {@linkplain BlockSetType} with the provided
     * identifier and configured identically to the Oak wood type.  This can be used as shorthand when no custom
     * configuration is desired.  The identifier's path should be identical to the name of related blocks
     * (f.e. "fir" for "traverse:fir_planks" etc.).
     *
     * @param typeId An identifier with the base name of the wood type
     * @return A registered WoodType ready to be used when creating a sign block
     */
    @PrivateUse
    public static WoodType registerDefaultWoodType(Identifier typeId) {
        DillonLib.debug("Registering wood type " + typeId + " with default configuration.");
        return WOOD_TYPE_CACHE.computeIfAbsent(typeId, id -> WoodTypeBuilder.copyOf(WoodType.OAK).register(id, BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(id)));
    }

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(Identifier id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    /**
     * Returns a registry key with the speedrunner mod namespace.
     */
    private static RegistryKey<EntityType<?>> keyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
    }

    /**
     * @return the set of {@code shield factories.}
     */
    @PrivateUse
    public static Set<ShieldData> getShieldFactories() {
        return SHIELD_FACTORIES;
    }

    /**
     * @return the map of {@code bow factories.}
     */
    @PrivateUse
    public static Map<BowFactory, Float> getBowFactories() {
        return BOW_FACTORIES;
    }

    /**
     * @return the set of {@code crossbow factories.}
     */
    @PrivateUse
    public static Set<CrossbowFactory> getCrossbowFactories() {
        return CROSSBOW_FACTORIES;
    }

    /**
     * @return the set of {@code shear factories.}
     */
    @PrivateUse
    public static Set<ShearsItem> getShearsFactories() {
        return SHEARS_FACTORIES;
    }

    /**
     * @return the set of {@code cutout blocks.}
     */
    @PrivateUse
    public static Set<Block> getCutoutBlocks() {
        return CUTOUT_BLOCKS;
    }

    /**
     * @return the set of {@code boats.}
     */
    @PrivateUse
    public static Set<BoatData> getBoatFactories() {
        return BOATS;
    }

    /**
     * Initializes the {@code registration} class.
     */
    public static void init() {
        DillonLib.info("Initialized registration class.");
    }
}