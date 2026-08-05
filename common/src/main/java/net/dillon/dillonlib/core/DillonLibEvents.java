package net.dillon.dillonlib.core;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.PacketListener;

/**
 * All registry events for DillonLib.
 * @see ModPlatform
 * @see DillonLibMain
 */
public class DillonLibEvents {

    /**
     * Registers all {@code common} events.
     */
    protected static void registerAllEvents() {
        PlatformLoader.executeForEachPlatform(ModPlatform::registerEvents);
        Platforms.getCommonPlatform().tickScheduledTasks();
    }

    /**
     * Registers all {@code client} player join events.
     */
    public static void registerAllClientJoinEvents(Minecraft minecraft, PacketListener clientPacketListener) {
        PlatformLoader.executeForEachClientPlatform(modPlatform -> modPlatform.registerClientJoinEvents(minecraft, clientPacketListener));
    }

    /**
     * Registers all {@code client} player disconnect events.
     */
    public static void registerAllClientDisconnectEvents(Minecraft minecraft) {
        PlatformLoader.executeForEachClientPlatform(modPlatform -> modPlatform.registerClientDisconnectEvents(minecraft));
    }

    /**
     * Registers all {@code common} commands.
     */
    public static void registerAllCommonCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
        PlatformLoader.executeForEachPlatform(modPlatform -> modPlatform.registerCommonCommands(commandDispatcher, commandRegistryAccess));
    }

    /**
     * Registers all {@code client} commands.
     */
    public static void registerAllClientCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
        PlatformLoader.executeForEachClientPlatform(modPlatform -> modPlatform.registerClientCommands(commandDispatcher, commandRegistryAccess));
    }

    /**
     * Registers all {@code server} commands.
     */
    public static void registerAllServerCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
        PlatformLoader.executeForEachPlatform(modPlatform -> modPlatform.registerServerCommands(commandDispatcher, commandRegistryAccess));
    }
}