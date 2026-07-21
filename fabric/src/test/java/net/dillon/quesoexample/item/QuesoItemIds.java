package net.dillon.quesoexample.item;

import net.dillon.quesoexample.platform.QuesoExamplePlatformGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class QuesoItemIds {
    public static final ResourceKey<Item> QUESO_SHIELD = create("queso_shield");
    public static final ResourceKey<Item> QUESO_BOW = create("queso_bow");
    public static final ResourceKey<Item> QUESO_CROSSBOW = create("queso_crossbow");
    public static final ResourceKey<Item> QUESO_SHEARS = create("queso_shears");
    public static final ResourceKey<Item> QUESO_FIRE = create("queso_fire");
    public static final ResourceKey<Item> QUESO_FLINT_AND_STEEL = create("queso_flint_and_steel");
    public static final ResourceKey<Item> QUESO_TOTEM = create("queso_totem");

    /**
     * Creates an {@code item id.}
     */
    private static ResourceKey<Item> create(String id) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(QuesoExamplePlatformGetter.get().modId(), id));
    }
}