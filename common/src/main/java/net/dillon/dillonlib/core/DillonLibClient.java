package net.dillon.dillonlib.core;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.platform.PlatformGetter;

import java.util.List;

/**
 * {@code Client} initialization entrypoint for DillonLib.
 */
@Dill(DillType.CLIENT)
public class DillonLibClient {

    /**
     * Initializes the {@code client-side} of DillonLib.
     */
    public static void cInitialize() {
        DillonLibClient.clientInitializers().forEach(Runnable::run);

        PlatformGetter.getDillonLibPlatform().logger().info("Client-side for DillonLib has successfully initialized.");
    }

    /**
     * @return all client-side initializer methods for {@code client-side only classes.}
     */
    private static List<Runnable> clientInitializers() {
        return List.of(
                ClientFactories::i_
        );
    }
}