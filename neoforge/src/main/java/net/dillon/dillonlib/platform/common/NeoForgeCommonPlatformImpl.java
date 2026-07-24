package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.item.SimpleItemGroupFactory;
import net.dillon.dillonlib.task.CommonTasks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class NeoForgeCommonPlatformImpl extends CommonModPlatform {
    public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            DillonLibMain.MOD_ID
    );
    private static final Map<ResourceKey<CreativeModeTab>, List<Supplier<ItemStack>>> CREATIVE_TAB_ITEMS = new HashMap<>();

    @Override
    public @NotNull Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isEnvironmentClient() {
        return FMLEnvironment.dist.isClient();
    }

    @Override
    public boolean isEnvironmentServer() {
        return FMLEnvironment.dist.isDedicatedServer();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.production;
    }

    @Override
    public void tickScheduledTasks() {
        NeoForge.EVENT_BUS.addListener((ServerTickEvent.Post event) -> {
            CommonTasks.tickTasks(event.getServer());
        });
    }

    @Override
    public void registerItemGroup(SimpleItemGroupFactory simpleItemGroup) {
        try {
            ITEM_GROUPS.register(
                    simpleItemGroup.id().getPath(),
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup." + simpleItemGroup.id().getNamespace() + "." + simpleItemGroup.id().getPath()))
                            .icon(() -> new ItemStack(simpleItemGroup.icon()))
                            .displayItems((parameters, output) -> {
                                simpleItemGroup.entries().get().forEach(output::accept);
                            })
                            .build());
        } catch (IllegalArgumentException | IllegalStateException o) { // Stops duplicate groups from being created
        }
    }

    @Override
    public void addItemToGroup(ResourceKey<CreativeModeTab> tab, Supplier<ItemStack> stack) {
        CREATIVE_TAB_ITEMS
                .computeIfAbsent(tab, ignored -> new ArrayList<>())
                .add(stack);
    }

    /**
     * Registers the creative mode tab event modification.
     */
    public static void addItemsToTab(BuildCreativeModeTabContentsEvent event) {
        List<Supplier<ItemStack>> items = CREATIVE_TAB_ITEMS.get(event.getTabKey());

        if (items == null) {
            return;
        }

        for (Supplier<ItemStack> item : items) {
            event.accept(item.get());
        }
    }
}