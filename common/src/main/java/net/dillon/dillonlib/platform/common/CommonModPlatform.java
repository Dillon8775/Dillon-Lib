package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.Loadable;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.task.CommonTasks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * Platform class which always returns the same value for specific platforms, meaning it's not customizable by your mod. This class is not an instance of loadable, as it does not check for multiple instances of these classes. You should not create your own instance of this platform, because this platform is meant to be final.
 * @since 1.0
 * @see Loadable
 * @see ModPlatform
 */
public abstract class CommonModPlatform {

    /**
     * Gets the config directory for the supported platform.
     */
    public abstract @NotNull Path configDir();

    /**
     * @return if the environment side is client.
     */
    public abstract boolean isEnvironmentClient();

    /**
     * @return if the environment side is server.
     */
    public abstract boolean isEnvironmentServer();

    /**
     * @return if the game was launched in a development environment.
     */
    public abstract boolean isDevelopmentEnvironment();

    /**
     * Ticks all simple scheduled tasks.
     * @see CommonTasks
     */
    public abstract void tickScheduledTasks();

    /**
     * Refreshes all item groups when an item group factory is created. Cannot be done on forge 1.20.1.
     * @see Factories#registerSimpleItemGroupFactory(ResourceLocation, ItemLike, Supplier) 
     */
    public abstract void refreshItemGroups();

    /**
     * Adds an item to an item group.
     * @see Factories
     */
    public abstract void addItemToGroup(ResourceKey<CreativeModeTab> tab, Supplier<ItemStack> stack);
}