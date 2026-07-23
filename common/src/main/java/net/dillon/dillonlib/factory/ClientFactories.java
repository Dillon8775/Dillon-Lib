package net.dillon.dillonlib.factory;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.client.ClientPlatformGetter;
import net.minecraft.client.KeyMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores client-side factories for different Minecraft items and blocks.
 * @since 1.0
 */
@Dill(DillType.CLIENT)
public class ClientFactories {
    public static final Map<KeyMapping, Runnable> NON_KUMA_KEY_MAPPING_FACTORIES = new HashMap<>();

    /**
     * Registers a {@code key binding factory,} with an functional action and a message to send.
     * @param category the group that this keybind should be in, found in the "Controls" menu.
     * @param key the translation key for the keybind
     * @param code the keycode for the keybind (see {@link org.lwjgl.glfw.GLFW}.)
     * @param runnable the {@link Runnable} that the keybind should execute (typically in-game).
     */
    public static KeyMapping registerKeyMapping(String key, InputConstants.Type type, String category, int code, Runnable runnable) {
        KeyMapping keyBind = ClientPlatformGetter.getDillonLibClientPlatform().createKeyMapping(key, type, category, code);
        NON_KUMA_KEY_MAPPING_FACTORIES.put(keyBind, runnable);
        DillonLibMain.LOGGER.info("Registered key mapping {} with keycode {}", key, code);
        return keyBind;
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}