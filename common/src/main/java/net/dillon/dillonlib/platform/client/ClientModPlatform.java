package net.dillon.dillonlib.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Loadable;
import net.dillon.dillonlib.platform.ModPlatform;
import net.minecraft.client.KeyMapping;

/**
 * An instance of {@link Loadable}. Similar to {@link ModPlatform}, all methods used inside this class should be {@code client-side} only methods, meaning they should not interfere with the common code.
 * @since 1.0
 * @see Loadable
 * @see ModPlatform
 */
@Dill(DillType.CLIENT)
public abstract class ClientModPlatform implements Loadable {

    /**
     * @return the mod id for this client platform. Should return your mod id from your common mod class.
     */
    @Override
    public abstract String modId();

    /**
     * Registers a {@link KeyMapping} into the game. This should point to your platform's way of registering a keybind.
     */
    public abstract KeyMapping createKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value);
}