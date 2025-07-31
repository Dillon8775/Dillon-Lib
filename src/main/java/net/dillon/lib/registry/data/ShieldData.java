package net.dillon.lib.registry.data;

import net.minecraft.item.ShieldItem;
import net.minecraft.util.Identifier;

/**
 * Stores {@code shield data.}
 */
public record ShieldData(Identifier identifier, ShieldItem shield, int cooldown) {
}