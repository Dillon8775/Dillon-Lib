package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.task.CommonTasks;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.function.Supplier;

public class FabricCommonPlatformImpl extends CommonModPlatform {

    @Override
    public @NotNull Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isEnvironmentClient() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);
    }

    @Override
    public boolean isEnvironmentServer() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void tickScheduledTasks() {
        ServerTickEvents.END_SERVER_TICK.register(CommonTasks::tickTasks);
    }

    @Override
    public void refreshItemGroups() {
        Factories.SIMPLE_ITEM_GROUPS.forEach((simpleItemGroup) -> {
            try {
                ResourceKey<CreativeModeTab> key = ResourceKey.create(
                        Registries.CREATIVE_MODE_TAB,
                        simpleItemGroup.id()
                );

                Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key,
                        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                                .title(Component.translatable("itemGroup." + simpleItemGroup.id().getNamespace() + "." + simpleItemGroup.id().getPath()))
                                .icon(() -> new ItemStack(simpleItemGroup.icon()))
                                .displayItems((parameters, output) -> {
                                    simpleItemGroup.entries().get().forEach(output::accept);
                                })
                                .build()
                );
            } catch (IllegalArgumentException | IllegalStateException o) { // Stops duplicate groups from being created
            }
        });
    }

    @Override
    public void addItemToGroup(ResourceKey<CreativeModeTab> tab, Supplier<ItemStack> stack) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.accept(stack.get()));
    }
}