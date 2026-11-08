package net.dillon.dillonlib.platform.universal;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.KeyMapping;

/**
 * Platform class which always returns the same value for specific platforms, specifically for the client-side.
 * @since 1.3
 * @see UniversalModPlatform
 */
@Dill(env = DillEnv.CLIENT)
public abstract class ClientUniversalModPlatform {

    /**
     * Registers a {@link KeyMapping} into the game.
     */
    public abstract KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value);
}