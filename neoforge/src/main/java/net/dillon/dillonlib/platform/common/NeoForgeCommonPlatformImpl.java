package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.TaskScheduler;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class NeoForgeCommonPlatformImpl extends CommonModPlatform {

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
            TaskScheduler.tick(event.getServer());
        });
    }
}