package net.dillon.dillonlib;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.Minecraft;

/**
 * Client-side only methods and tasks that can be commonly used throughout your mod.
 * @since 1.0
 */
@Dill(DillType.CLIENT)
public class ClientTasks {

    /**
     * Stops and closes the game, with the option to run a bit of code before.
     */
    public static void stop(Runnable runnable) {
        runnable.run();
        Minecraft.getInstance().stop();
    }
}