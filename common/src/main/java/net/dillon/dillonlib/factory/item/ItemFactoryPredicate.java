package net.dillon.dillonlib.factory.item;

import net.minecraft.world.item.ItemStack;

/**
 * Represents a {@code item factory}, which is tested in areas of the game for certain predicates.
 * @since 1.0
 */
@FunctionalInterface
public interface ItemFactoryPredicate {
    boolean test(ItemStack stack);
}