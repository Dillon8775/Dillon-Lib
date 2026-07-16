package net.dillon.dillonlib.utility;

import net.minecraft.client.Minecraft;

/**
 * Client-side only methods that can be commonly used throughout your mod.
 */
public class ClientChores {

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

    /**
     * Stops and closes the game, with the option to run a bit of code before.
     */
    public static void stop(Runnable runnable) {
        runnable.run();
        Minecraft.getInstance().stop();
    }
}