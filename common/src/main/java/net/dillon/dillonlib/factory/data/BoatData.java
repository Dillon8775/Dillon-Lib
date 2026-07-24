package net.dillon.dillonlib.factory.data;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.ItemLike;

/**
 * Holds boat registration data.
 * @since 1.0
 * @param entityType the actual registered and stored boat entity type
 * @param id the id for the boat entity (including the mod's namespace and entity id)
 * @param chest determines if the boat entity should be a chest boat
 */
public record BoatData(EntityType<? extends AbstractBoat> entityType, ItemLike dropItem, Identifier id, boolean chest) {
}