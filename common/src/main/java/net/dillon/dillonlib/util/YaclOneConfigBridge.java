package net.dillon.dillonlib.util;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.Supplier;

/**
 * A compat bridge class to interlink {@code YACL} configuration and {@code OneConfig} and automatically parse the configs together.
 * @since 1.3
 */
public class YaclOneConfigBridge {
    private static final Map<String, Supplier<Object>> modsToParse = new HashMap<>();
    private static final Set<String> reparsingMods = new HashSet<>();
    private static final String YACL_COMPAT_CLASS = "org.polyfrost.oneconfig.internal.compat.YACLCompat";
    private static final String COMPAT_LOADER_CLASS = "org.polyfrost.oneconfig.internal.compat.CompatLoader";

    /**
     * Creates an initial parse bridge for a {@code YACl} screen to a {@code OneConfig} screen.
     */
    public static void createInitialParseBridge(String modId, Supplier<Object> yaclInstance) {
        modsToParse.put(modId, yaclInstance);
    }

    /**
     * @return the map of mods that need to be parsed.
     */
    public static Map<String, Supplier<Object>> getModsToParse() {
        return modsToParse;
    }

    /**
     * Parses {@code YACL} screens into {@code OneConfig} screens on game launch (internal use only).
     */
    public static boolean parse(String modId, Object yaclInstance) {
        try {
            withForcedModId(modId, () -> {
                try {
                    getYACLParser().invoke(null, yaclInstance);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException(e);
                }

                return null;
            });

            return true;
        } catch (RuntimeException | LinkageError e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reparses a config screen when a screen is saved (internal use only). Call this in your {@code YACL.save} block.
     */
    public static void reparse(String modId, Object yaclInstance) {
        // Return out of not reparsing mod or OneConfig isn't loaded
        if (!YaclOneConfigBridge.loaded() || !reparsingMods.add(modId)) {
            return;
        }

        try {
            Object compatLoader = getCompatLoader();

            Collection<?> loadedConfigs = (Collection<?>) compatLoader.getClass()
                    .getMethod("getNativeLoadedConfigs")
                    .invoke(compatLoader);

            loadedConfigs.remove(modId);

            parse(modId, yaclInstance);
        } catch (ReflectiveOperationException | LinkageError e) {
            e.printStackTrace();
        } finally {
            reparsingMods.remove(modId);
        }
    }

    /**
     * @return the action when parsing a config.
     */
    @SuppressWarnings("unchecked")
    public static <T> T withForcedModId(String modId, Supplier<T> action) {
        try {
            ClassLoader classLoader = getClassLoader();

            Class<?> function0Class = Class.forName(
                    "kotlin.jvm.functions.Function0",
                    false,
                    classLoader
            );

            Object unit = Class.forName("kotlin.Unit", false, classLoader)
                    .getField("INSTANCE")
                    .get(null);

            Object[] result = new Object[1];

            Object callback = Proxy.newProxyInstance(
                    classLoader,
                    new Class<?>[]{function0Class},
                    (proxy, method, args) -> {
                        if (method.getName().equals("invoke")) {
                            result[0] = action.get();
                            return unit;
                        }

                        return null;
                    }
            );

            Object compatLoader = getCompatLoader();

            compatLoader.getClass()
                    .getMethod("withForcedModId", String.class, function0Class)
                    .invoke(compatLoader, modId, callback);

            return (T) result[0];
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Failed to execute with forced OneConfig mod ID", e);
        }
    }

    /**
     * @return the class loader for {@link YaclOneConfigBridge}.
     */
    private static ClassLoader getClassLoader() {
        return YaclOneConfigBridge.class.getClassLoader();
    }

    /**
     * Checks whether {@code OneConfig} is loaded correctly.
     */
    public static boolean loaded() {
        try {
            getYACLParser();
            return true;
        } catch (ReflectiveOperationException | LinkageError ignored) {
            return false;
        }
    }

    /**
     * @return the compat loader in {@code OneConfig}, which stores parsed configs.
     * @throws ReflectiveOperationException when OneConfig isn't loaded.
     */
    private static Object getCompatLoader() throws ReflectiveOperationException {
        ClassLoader classLoader = getClassLoader();

        Class<?> compatLoaderClass = Class.forName(
                COMPAT_LOADER_CLASS,
                false,
                classLoader
        );

        return compatLoaderClass.getField("INSTANCE").get(null);
    }

    /**
     * @return the parser for {@code YACL}.
     * @throws ReflectiveOperationException when {@code OneConfig} isn't loaded.
     */
    private static Method getYACLParser() throws ReflectiveOperationException {
        Class<?> compatClass = Class.forName(
                YACL_COMPAT_CLASS,
                false,
                YaclOneConfigBridge.class.getClassLoader()
        );

        return compatClass.getMethod("parseYACL", Object.class);
    }
}