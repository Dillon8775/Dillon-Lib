package net.dillon.dillonlib.factory;

import net.dillon.dillonlib.factory.data.BoatData;
import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

import java.util.HashSet;
import java.util.Set;

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
     * @return the registered boat entity type
     */
    public static <T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, EntityType.EntityFactory<T> factory, boolean chest) {
        EntityType<T> boat = register(
                ResourceKey.create(Registries.ENTITY_TYPE, id),
                EntityType.Builder.of(factory, MobCategory.MISC)
                        .noLootTable()
                        .sized(1.375F, 0.5625F)
                        .eyeHeight(0.5625F)
                        .clientTrackingRange(10)
        );

        BOATS.add(new BoatData(boat, id, chest));
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