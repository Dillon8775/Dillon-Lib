package net.dillon.dillonlib.mixin.accessor;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

/**
 * Accesses the {@code items} variable in {@link MatchTool}, to allow shear predicates to work.
 * @since 1.0 (1.20.1 only)
 */
@Mixin(ItemPredicate.class)
public interface ItemPredicateAccessor {
    @Accessor("items")
    Set<Item> getItems();
}