package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.dillonlib.platform.universal.ClientUniversalModPlatform;
import net.dillon.dillonlib.platform.universal.UniversalModPlatform;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Contains all built-in DillonLib platforms to access for your convenience.
 */
public class Platforms {
    private static final UniversalModPlatform UNIVERSAL_PLATFORM = loadUniversalPlatform();
    private static final ClientUniversalModPlatform CLIENT_UNIVERSAL_PLATFORM = loadClientCommonPlatform();
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, DillonLibMain.MOD_ID);
    private static final MixinModPlatform MIXIN_PLATFORM = PlatformLoader.load(MixinModPlatform.class, DillonLibMain.MOD_ID);
    private static final ClientModPlatform CLIENT_PLATFORM = PlatformLoader.load(ClientModPlatform.class, DillonLibMain.MOD_ID);

    public static UniversalModPlatform getUniversalPlatform() {
        return UNIVERSAL_PLATFORM;
    }

    public static ClientUniversalModPlatform getClientUniversalPlatform() {
        return CLIENT_UNIVERSAL_PLATFORM;
    }

    public static ModPlatform getDillonLibPlatform() {
        return PLATFORM;
    }

    public static MixinModPlatform getDillonLibMixinPlatform() {
        return MIXIN_PLATFORM;
    }

    public static ClientModPlatform getDillonLibClientPlatform() {
        return CLIENT_PLATFORM;
    }

    private static UniversalModPlatform loadUniversalPlatform() {
        return ServiceLoader.load(UniversalModPlatform.class)
                .findFirst()
                .orElseThrow();
    }

    private static ClientUniversalModPlatform loadClientCommonPlatform() {
        return ServiceLoader.load(ClientUniversalModPlatform.class)
                .findFirst()
                .orElseThrow();
    }

    /**
     * @return all service loaders with a specified class. You can use this method in combo with {@link forEachPlatform(Consumer)} to execute a task for all instances of a certain platform (an instance of {@link Loadable}.
     * @see Loadable
     */
    public static <T> List<T> collectionOf(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();
    }

    /**
     * Executes a given task (or method) for all instances {@link ModPlatform} classes, and then returns the total amount of platforms (excluding DillonLib's base platform).
     */
    public static int forEachPlatform(Consumer<ModPlatform> function) {
        List<ModPlatform> platforms = collectionOf(ModPlatform.class);

        for (ModPlatform platform : platforms) {
            function.accept(platform);
        }

        return platforms.size() - 1;
    }

    /**
     * Executes a given task (or method) for all instances {@link MixinModPlatform} classes, and then returns the total amount of mixin platforms (excluding DillonLib's base mixin platform).
     */
    public static int forEachMixinPlatform(Consumer<MixinModPlatform> function) {
        List<MixinModPlatform> mixinPlatforms = collectionOf(MixinModPlatform.class);

        for (MixinModPlatform mixinPlatform : mixinPlatforms) {
            function.accept(mixinPlatform);
        }

        return mixinPlatforms.size() - 1;
    }

    /**
     * Executes a given task (or method) for all instances {@link ClientModPlatform} classes, and then returns the total amount of client platforms (excluding DillonLib's base client platform).
     */
    public static int forEachClientPlatform(Consumer<ClientModPlatform> function) {
        List<ClientModPlatform> clientPlatforms = collectionOf(ClientModPlatform.class);

        for (ClientModPlatform clientPlatform : clientPlatforms) {
            function.accept(clientPlatform);
        }

        return clientPlatforms.size() - 1;
    }

    /**
     * @return if something should be applied base on any {@link MixinModPlatform}s value.
     */
    public static boolean mixinPlatformPredicate(Predicate<MixinModPlatform> mixinModPlatformPredicate) {
        try {
            List<MixinModPlatform> mixinPlatforms = ServiceLoader.load(MixinModPlatform.class)
                    .stream()
                    .map(ServiceLoader.Provider::get)
                    .filter(mixinModPlatformPredicate)
                    .toList();

            // Meaning if a mixin platform that returns true for any method, return true
            if (!mixinPlatforms.isEmpty()) {
                return true;
            }
        } catch (NoSuchElementException o) {
            return false;
        }

        return false;
    }

    /**
     * @return if {@code factories} should be applied into the game, which determines if certain mixin fixes should be applied.
     */
    public static boolean shouldApplyFactories() {
        return mixinPlatformPredicate(MixinModPlatform::shouldApplyFactories);
    }

    /**
     * @return if {@code full bright} should be applied to the game.
     */
    public static boolean shouldApplyFullBright() {
        return mixinPlatformPredicate(MixinModPlatform::shouldApplyFullBright);
    }
}