package net.dillon.dillonlib.factory;

import net.dillon.dillonlib.factory.data.BoatData;
import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixin.accessor.EntityTypesInvoker;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Stores different factories for Minecraft items and blocks, to register most of the things needed for that special item with one single method.
 * <p>See {@link net.dillon.dillonlib.factory.item} for more item-specific factories.</p>
 * @since 1.0
 * @see net.dillon.dillonlib.mixin
 */
public class Factories {
    public static final Set<BoatData> BOATS = new HashSet<>();
    public static final Set<ShearsFactory> SHEARS = new HashSet<>();
    public static final Set<IgnitableFactory.FlintAndSteel> FLINT_AND_STEELS = new HashSet<>();

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

    /**
     * Registers a {@code boat entity factory} into the game.
     * @param id the {@link Identifier} for the boat entity
     * @param chest determines if the boat is a {@code chest boat}
     * @param dropItem the item that is dropped from the boat, and also registers dispenser behavior.
     * @return the registered boat entity type
     */
    public static <T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, Supplier<Item> dropItem, boolean chest) {
        return chest ?
                registerBoatFactory(id, dropItem, EntityTypesInvoker.registerModChestBoatFactory(dropItem), true) :
                registerBoatFactory(id, dropItem, EntityTypesInvoker.registerModBoatFactory(dropItem), false);
    }

    /**
     * Registers a custom-factory boat entity into the game.
     * @param factory the custom factory for the boat.
     * @return the custom-factory registered boat entity.
     */
    private static <T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, Supplier<Item> dropItem, EntityType.EntityFactory<T> factory, boolean chest) {
        EntityType<T> boat = register(
                ResourceKey.create(Registries.ENTITY_TYPE, id),
                EntityType.Builder.of(factory, MobCategory.MISC)
                        .noLootTable()
                        .sized(1.375F, 0.5625F)
                        .eyeHeight(0.5625F)
                        .clientTrackingRange(10)
        );

        BoatData boatData = new BoatData(boat, dropItem.get(), id, chest);
        DispenserBlock.registerBehavior(boatData.dropItem(), new BoatDispenseItemBehavior(boatData.entityType()));
        BOATS.add(boatData);
        PlatformGetter.getDillonLibPlatform().logger().info("Registered {} boat factory {}.", chest ? "chest" : "default", id);
        return boat;
    }

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }
}