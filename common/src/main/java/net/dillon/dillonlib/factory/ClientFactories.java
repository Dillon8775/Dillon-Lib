package net.dillon.dillonlib.factory;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Stores client-side factories for different Minecraft items and blocks.
 * @since 1.0
 */
@Dill(DillType.CLIENT)
public class ClientFactories {
    public static final Map<KeyMapping, Consumer<LocalPlayer>> NON_KUMA_KEY_MAPPING_FACTORIES = new HashMap<>();

    /**
     * Registers a {@code key binding factory,} with an functional action and a message to send.
     * @param category the group that this keybind should be in, found in the "Controls" menu.
     * @param key the translation key for the keybind
     * @param code the keycode for the keybind (see {@link org.lwjgl.glfw.GLFW}.)
     * @param consumer the {@link Consumer} that the keybind should execute (typically in-game).
     */
    public static KeyMapping createKeyMapping(String key, InputConstants.Type type, KeyMapping.Category category, int code, Consumer<LocalPlayer> consumer) {
        KeyMapping keyBind = Platforms.getDillonLibClientPlatform().registerKeyMapping(key, type, category, code);
        NON_KUMA_KEY_MAPPING_FACTORIES.put(keyBind, consumer);
        DillonLibMain.LOGGER.info("Registered key mapping {} with keycode {}", key, code);
        return keyBind;
    }

    /**
     * @return the texture path for a {@code normal boat.}
     */
    public static ModelLayerLocation boatModelLayer(String namespace, String id) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(namespace, "boat/" + id), "main");
    }

    /**
     * @return the texture path for a {@code chest boat.}
     */
    public static ModelLayerLocation chestBoatModelLayer(String namespace, String id) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(namespace, "chest_boat/" + id), "main");
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}