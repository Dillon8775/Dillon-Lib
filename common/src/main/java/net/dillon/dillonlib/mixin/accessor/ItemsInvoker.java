package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.factory.Factories;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Item and block invoker methods for registering blocks and items. You can use these methods for simplicity when registering your custom items/blocks, unless you have another way of registering them.
 * @since 1.0
 * @see Factories
 */
@Mixin(Items.class)
public interface ItemsInvoker {
    @Invoker("createBlockItemWithCustomItemName")
    static Function<Item.Properties, Item> createModBlockItemWithCustomItemName(Block block) {
        throw new AssertionError();
    }

    @Invoker("registerSpawnEgg")
    static Item registerModSpawnEgg(ResourceKey<Item> id, EntityType<?> type) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block, Item.Properties properties) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block, UnaryOperator<Item.Properties> propertiesFunction) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block, Block... alternatives) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block, BiFunction<Block, Item.Properties, Item> itemFactory) {
        throw new AssertionError();
    }

    @Invoker("registerBlock")
    static Item registerModBlock(BlockItemId id, Block block, BiFunction<Block, Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(ResourceKey<Item> id, Item.Properties properties) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(ResourceKey<Item> id) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(BlockItemId id, Function<Item.Properties, Item> itemFactory) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(ResourceKey<Item> id, Function<Item.Properties, Item> itemFactory) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(BlockItemId id, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new AssertionError();
    }

    @Invoker("registerItem")
    static Item registerModItem(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new AssertionError();
    }
}