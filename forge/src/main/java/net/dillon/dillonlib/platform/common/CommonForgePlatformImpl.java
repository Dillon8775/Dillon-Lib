package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.task.CommonTasks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.function.Supplier;

public class CommonForgePlatformImpl extends CommonModPlatform {

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
        MinecraftForge.EVENT_BUS.addListener((TickEvent.ServerTickEvent event) -> {
            if (event.phase == TickEvent.Phase.END) {
                CommonTasks.tickTasks(event.getServer());
            }
        });
    }

    @Override
    public void refreshItemGroups() {
    }

    public void addItemToGroup(ResourceKey<CreativeModeTab> tab, Supplier<ItemStack> stack) {
    }
}