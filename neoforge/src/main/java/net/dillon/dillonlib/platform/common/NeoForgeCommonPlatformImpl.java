package net.dillon.dillonlib.platform.common;

import net.dillon.dillonlib.task.CommonTasks;
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
        return FMLEnvironment.getDist().isClient();
    }

    @Override
    public boolean isEnvironmentServer() {
        return FMLEnvironment.getDist().isDedicatedServer();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.isProduction();
    }

    @Override
    public void tickScheduledTasks() {
        NeoForge.EVENT_BUS.addListener((ServerTickEvent.Post event) -> {
            CommonTasks.tickTasks(event.getServer());
        });
    }
}