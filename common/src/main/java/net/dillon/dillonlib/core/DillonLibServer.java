package net.dillon.dillonlib.core;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;

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

        DillonLibMain.LOGGER.info("(Server) DillonLib has loaded");
    }

    /**
     * @return all server-side initializer methods for {@code server-side only classes.}
     */
    private static List<Runnable> serverInitializers() {
        return List.of(
        );
    }
}