package net.dillon.lib.registry.data;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

/**
 * Holds boat data.
 */
public record BoatData(EntityType<BoatEntity> entityType, Identifier identifier, boolean chest) {
}