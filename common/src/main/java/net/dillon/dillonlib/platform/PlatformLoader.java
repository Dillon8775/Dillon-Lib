package net.dillon.dillonlib.platform;

import net.dillon.dillonlib.platform.common.CommonModPlatform;

import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Consumer;

/**
 * Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
 * environment to another. In the context of DillonLib we use this feature to access a mock API in the common code that
 * is swapped out for the platform-specific implementation at runtime.
 * @since 1.0
 */
public class PlatformLoader {
    private static final List<ModPlatform> ALL_PLATFORMS = collectionOf(ModPlatform.class);

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

    /**
     * This code is used to load a service for the current environment. Your implementation of the service must be defined
     * manually by including a text file in META-INF/services named with the fully qualified class name of the service.
     * Inside the file you should write the fully qualified class name of the implementation to load for the platform. For
     * example our file on Forge points to ForgePlatformImpl while Fabric points to FabricPlatformImpl.
     * @param modId should match {@link ModPlatform} or {@code MixinModPlatform} mod id!
     * @see ModPlatform
     * @see net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform
     * @see net.dillon.dillonlib.platform.client.ClientModPlatform
     * @see Loadable
     */
    public static <T> T load(Class<T> clazz, String modId) {
        return ServiceLoader.load(clazz)
                .stream()
                .map(ServiceLoader.Provider::get)
                .filter(platform -> {
                    if (platform instanceof CommonModPlatform) {
                        throw new IllegalStateException("Cannot load duplicate CommonModPlatform! There can only be one CommonModPlatform.");
                    }
                    return platform instanceof Loadable loadable && loadable.modId().equals(modId);
                })
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Failed to load instance of Loadable for mod \"" + modId + "\"!"));
    }

    /**
     * @return all service loaders with a specified class. You can use this method in combo with {@link PlatformLoader#executeForEachPlatform(Consumer)} to execute a task for all instances of a certain platform (an instance of {@link Loadable}.
     * @see Loadable
     */
    public static <T> List<T> collectionOf(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();
    }

    /**
     * Executes a given task (or method) for all instances {@link ModPlatform} classes.
     */
    public static void executeForEachPlatform(Consumer<ModPlatform> function) {
        for (ModPlatform platform : ALL_PLATFORMS) {
            function.accept(platform);
        }
    }
}