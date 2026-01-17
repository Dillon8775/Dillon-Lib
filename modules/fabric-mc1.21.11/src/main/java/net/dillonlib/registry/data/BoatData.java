package net.dillonlib.registry.data;

import net.dillonlib.annotation.PrivateUse;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

/**
 * Holds boat data.
 * @param entityType the actual registered and stored boat entity type
 * @param identifier the identifier for the boat entity (including the mod's namespace and entity name)
 * @param chest determines if the boat entity should be a chest boat
 */
@PrivateUse
public record BoatData(EntityType<BoatEntity> entityType, Identifier identifier, boolean chest) {
}