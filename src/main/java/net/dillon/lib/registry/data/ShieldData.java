package net.dillon.lib.registry.data;

import net.dillon.lib.annotation.GlobalUse;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Identifier;

/**
 * Stores {@code shield data.}
 * @param identifier the identifier for the shield (including the mod's namespace and item name)
 * @param shield the actual registered and stored shield item
 * @param cooldown the cooldown for the shield when disabled by an axe
 */
@GlobalUse
public record ShieldData(Identifier identifier, ShieldItem shield, int cooldown) {
}