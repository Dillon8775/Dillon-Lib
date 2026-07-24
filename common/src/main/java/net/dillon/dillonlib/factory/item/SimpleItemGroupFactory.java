package net.dillon.dillonlib.factory.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Supplier;

/**
 * Holds item group data that is registered when creating an item group factory.
 */
public record SimpleItemGroupFactory(Identifier id, ItemLike icon, Supplier<List<ItemStack>> entries) {}