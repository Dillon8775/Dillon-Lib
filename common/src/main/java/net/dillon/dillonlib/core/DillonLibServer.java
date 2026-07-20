package net.dillon.dillonlib.core;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.PlatformGetter;

import java.util.List;

/**
 * {@code Server} initialization entrypoint for DillonLib.
 */
@Dill(DillType.DEDICATED_SERVER)
public class DillonLibServer {

    /**
     * Initializes the {@code server-side} of DillonLib.
     */
    public static void sInitialize() {
        DillonLibServer.serverInitializers().forEach(Runnable::run);

        PlatformGetter.getDillonLibPlatform().logger().info("Server-side for DillonLib has successfully initialized.");
    }

    /**
     * @return all server-side initializer methods for {@code server-side only classes.}
     */
    private static List<Runnable> serverInitializers() {
        return List.of(
        );
    }
}