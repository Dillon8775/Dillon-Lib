package net.dillon.dillonlib.factory;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.Platforms;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Stores client-side factories for different Minecraft items and blocks.
 * @since 1.0
 */
@Dill(env = DillEnv.CLIENT)
public class ClientFactories {
    public static final Map<KeyMapping, Consumer<LocalPlayer>> NON_KUMA_KEY_MAPPING_FACTORIES = new HashMap<>();

    /**
     * Registers a {@code key binding factory,} with an functional action and a message to send.
     * @param category the group that this keybind should be in, found in the "Controls" menu.
     * @param key the translation key for the keybind.
     * @param code the keycode for the keybind (see {@link org.lwjgl.sdl.SDLKeycode}).
     * @param consumer the {@link Consumer} that the keybind should execute (typically in-game).
     * @return the registered keybinding.
     */
    public static KeyMapping createKeyMapping(String key, InputConstants.Type type, KeyMapping.Category category, int code, Consumer<LocalPlayer> consumer) {
        KeyMapping keyBind = Platforms.getClientUniversalPlatform().registerKeyMapping(key, type, category, code);
        NON_KUMA_KEY_MAPPING_FACTORIES.put(keyBind, consumer);
        DillonLibMain.LOGGER.info("Registered key mapping {} with keycode {}", key, code);
        return keyBind;
    }

    /**
     * Registers a {@link DebugScreenEntry} key.
     * @param namespace the mod namespace for your key.
     * @param path the name for your entry.
     * @return the registered entry key.
     */
    public static ResourceKey<DebugScreenEntry> createDebugEntryKey(String namespace, String path) {
        return createDebugEntryKey(Identifier.fromNamespaceAndPath(namespace, path));
    }

    /**
     * Registers a {@link DebugScreenEntry} key.
     * @param identifier the identifier for your key.
     * @return the registered entry key.
     */
    public static ResourceKey<DebugScreenEntry> createDebugEntryKey(Identifier identifier) {
        return ResourceKey.create(ClientRegistries.DEBUG_SCREEN_ENTRY, identifier);
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}