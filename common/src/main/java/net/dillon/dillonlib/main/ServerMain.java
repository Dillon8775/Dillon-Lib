package net.dillon.dillonlib.main;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Statics;

import java.util.List;

/**
 * {@code Server} initialization entrypoint for DillonLib.
 */
@Dill(DillType.DEDICATED_SERVER)
public class ServerMain {

    /**
     * Initializes the {@code server-side} of DillonLib.
     */
    public static void sInitialize() {
        serverInitializers().forEach(Runnable::run);

        Statics.info("Server-side for DillonLib has successfully initialized.");
    }

    /**
     * @return all server-side initializer methods for {@code server-side only classes.}
     */
    private static List<Runnable> serverInitializers() {
        return List.of(
        );
    }
}