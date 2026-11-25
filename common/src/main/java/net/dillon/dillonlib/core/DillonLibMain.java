package net.dillon.dillonlib.core;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.dillonlib.util.ScheduledTask;
import net.dillon.dillonlib.util.UpdateChecker;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * {@code Main} initialization entrypoint for DillonLib.
 */
public class DillonLibMain {
    public static final String MOD_ID = "dillonlib";
    public static final Logger LOGGER = LoggerFactory.getLogger("DillonLib/Main");
    public static boolean HAS_UPDATE = UpdateChecker.hasUpdate(UpdateChecker.checkForUpdate(
            "dillon-lib",
            Platforms.getUniversalPlatform().commonModVersion(DillonLibMain.MOD_ID))
    );

    /**
     * Initializes DillonLib on {@code all environment sides}.
     */
    public static void initialize() {
        DillonLibOptions.INSTANCE.load();

        DillonLibMain.commonInitializers().forEach(Runnable::run);

        Platforms.forEachPlatform(ModPlatform::registerables);

        int platforms = Platforms.forEachPlatform(modPlatform -> {
            if (!modPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                LOGGER.info("ModPlatform loaded with mod ID {} (version {})",
                        modPlatform.modId(),
                        modPlatform.modVersion()
                );
            }
        });
        int mixinPlatforms = Platforms.forEachMixinPlatform(mixinModPlatform -> {
            if (!mixinModPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                LOGGER.info("MixinModPlatform loaded with mod ID {} (factories={}, fullBright={})",
                        mixinModPlatform.modId(),
                        mixinModPlatform.shouldApplyFactories(),
                        mixinModPlatform.shouldApplyFullBright()
                );
            }
        });

        LOGGER.info("Loaded {} platforms and {} mixin platforms",
                platforms,
                mixinPlatforms
        );

        LOGGER.info("DillonLib {} for {} has loaded",
                Platforms.getDillonLibPlatform().modVersion(),
                Platforms.getDillonLibPlatform().platform().toString().toLowerCase(Locale.ROOT));
    }

    /**
     * @return all common initializer methods for {@code universal environment classes.}
     */
    private static List<Runnable> commonInitializers() {
        return List.of(
                PlatformLoader::i_,
                Factories::i_
        );
    }

    /**
     * @return an identifier from DillonLib.
     */
    public static Identifier dillonLibIdentifier(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /**
     * Event-handling for all environments of DillonLib.
     */
    public static class Events {

        /**
         * Registers all {@code common} commands.
         */
        public static void registerAllCommonCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess) {
            Platforms.forEachPlatform(modPlatform -> modPlatform.registerCommonCommands(commandDispatcher, commandRegistryAccess));
        }

        /**
         * Runs all universal game ticking events for each platform.
         */
        public static void layCommonTicking() {
            Platforms.forEachPlatform(ModPlatform::gameTick);

            DillonLibMain.Events.tickTasks(CommonTasks.getQueuedCommonTasks());
        }

        /**
         * Ticks any set of tasks.
         */
        protected static void tickTasks(List<ScheduledTask> tasks) {
            Iterator<ScheduledTask> iterator = tasks.iterator();

            while (iterator.hasNext()) {
                ScheduledTask task = iterator.next();
                task.ticks--;

                if (task.ticks <= 0) {
                    task.runnable.run();
                    iterator.remove();
                }
            }
        }
    }
}