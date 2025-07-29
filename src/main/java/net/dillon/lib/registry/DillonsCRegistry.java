package net.dillon.lib.registry;

import net.dillon.lib.DillonLib;
import net.dillon.lib.annotation.GlobalUse;
import net.dillon.lib.annotation.PrivateUse;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A client-side registry class, which registers certain items to a list to register in other parts of the game to behave correctly on client-side.
 */
@Environment(EnvType.CLIENT)
@GlobalUse
public class DillonsCRegistry {
    private static final Map<KeyBinding, List<Runnable>> KEY_BINDING_FACTORIES = new HashMap<>();

    /**
     * Registers a {@code key binding factory,} with only an action.
     */
    @GlobalUse
    public static KeyBinding registerKeyBinding(String group, String key, int code, Runnable runnable) {
        return registerKeyBinding(group, key, code, runnable, null);
    }

    /**
     * Registers a {@code key binding factory,} with an functional action and a message to send.
     * @param group the group that this keybind should be in, found in the "Controls" menu.
     * @param key the translation key for the keybind
     * @param code the keycode for the keybind (see {@link GLFW})
     * @param runnable the {@link Runnable} that the keybind should execute (typically in-game).
     * @param message the message that should be sent when the player uses the keybind (if message is null, then no message is sent).
     */
    @GlobalUse
    public static KeyBinding registerKeyBinding(String group, String key, int code, Runnable runnable, @Nullable Text message) {
        KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(key, InputUtil.Type.KEYSYM, code, group));
        Runnable sendMessage = () -> {
            if (MinecraftClient.getInstance().inGameHud != null) {
                MinecraftClient.getInstance().inGameHud.getChatHud()
                        .addMessage((Text.literal("")).copy()
                                .append((Text.translatable("debug.prefix")).formatted(Formatting.YELLOW, Formatting.BOLD))
                                .append(" ").append(message));
            }
        };
        List<Runnable> runnables = new ArrayList<>();
        runnables.add(runnable);
        if (message != null) {
            runnables.add(sendMessage);
        }
        KEY_BINDING_FACTORIES.put(keyBind, runnables);
        return keyBind;
    }

    /**
     * @return the key binding factories.
     */
    @PrivateUse
    public static Map<KeyBinding, List<Runnable>> getKeyBindingFactories() {
        return KEY_BINDING_FACTORIES;
    }

    @PrivateUse
    public static void init() {
        DillonLib.info("Initialized client-registry class.");
    }
}