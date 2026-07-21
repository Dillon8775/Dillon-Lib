package net.dillon.quesoexample.entity;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.mixin.accessor.EntityTypesInvoker;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.item.QuesoTestItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

public class QuesoEntityTypes {
    public static final EntityType<Boat> QUESO_BOAT = Factories.registerBoatFactory(
            Identifier.fromNamespaceAndPath(QuesoExampleMod.MOD_ID, "queso_boat"), () -> QuesoTestItems.QUESO_BOAT, false);

    public static final EntityType<ChestBoat> QUESO_CHEST_BOAT = Factories.registerBoatFactory(
            Identifier.fromNamespaceAndPath(QuesoExampleMod.MOD_ID, "queso_chest_boat"), () -> QuesoTestItems.QUESO_CHEST_BOAT, true);

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}