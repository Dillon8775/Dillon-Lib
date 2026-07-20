package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.TaskScheduler;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

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
        ServerTickEvents.END_SERVER_TICK.register(TaskScheduler::tick);
    }
}