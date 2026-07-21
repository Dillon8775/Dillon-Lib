package net.dillon.dillonlib.core;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.TaskScheduler;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.common.CommonPlatformGetter;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.dispenser.FlintAndSteelDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * All registry events for DillonLib.
 * @see ModPlatform
 * @see DillonLibMain
 */
public class DillonLibEvents {

    /**
     * Registers all dispenser behaviors from item factories.
     */
    protected static void registerDispenserBehaviors() {
        for (ShearsFactory shear : Factories.SHEARS) {
            if (shear.registerDispenserBehavior) {
                DispenserBlock.registerBehavior(shear, new ShearsDispenseItemBehavior());
            }
        }
        PlatformGetter.getDillonLibPlatform().logger().debug("Registered shear factory dispenser behaviors.");

        for (IgnitableFactory.FlintAndSteel flintAndSteel : Factories.FLINT_AND_STEELS) {
            if (flintAndSteel.registerDispenserBehavior) {
                DispenserBlock.registerBehavior(flintAndSteel, new FlintAndSteelDispenseItemBehavior());
            }
        }
        PlatformGetter.getDillonLibPlatform().logger().debug("Registered flint and steel factory dispenser behaviors.");
    }

    /**
     * Ticks all scheduled tasks.
     * @see TaskScheduler
     */
    protected static void tickAllScheduledTasks() {
        CommonPlatformGetter.get().tickScheduledTasks();
    }

    /**
     * Registers all {@code common} events.
     */
    protected static void registerAllCommonEvents() {
        PlatformLoader.executeForEachPlatform(ModPlatform::registerEvents);
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
        PlatformLoader.executeForEachPlatform(modPlatform -> modPlatform.registerClientCommands(commandDispatcher, commandRegistryAccess));
    }

    /**
     * Registers all {@code server} commands.
     */
    public static void registerAllServerCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
        PlatformLoader.executeForEachPlatform(modPlatform -> modPlatform.registerServerCommands(commandDispatcher, commandRegistryAccess));
    }
}