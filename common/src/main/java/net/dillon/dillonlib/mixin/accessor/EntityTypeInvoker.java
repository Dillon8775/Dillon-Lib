package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.factory.Factories;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

/**
 * Invoker for registering boat and chest boat factories to map the boat item to the boat's entity type. You should use these methods when registering your boat.
 * @since 1.0
 * @see Factories
 */
@Mixin(EntityType.class)
public interface EntityTypeInvoker {
    @Invoker("boatFactory")
    static <T extends AbstractBoat> EntityType.EntityFactory<T> registerModBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }

    @Invoker("chestBoatFactory")
    static <T extends AbstractBoat> EntityType.EntityFactory<T> registerModChestBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }
}