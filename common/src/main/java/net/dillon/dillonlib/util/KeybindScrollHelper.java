package net.dillon.dillonlib.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utility class for scrolling to a certain keybinds list.
 */
public class KeybindScrollHelper {
    private static KeyMapping.Category pendingCategory = null;

    /**
     * Requests and queues a keybind category string.
     */
    public static void request(KeyMapping.Category category) {
        pendingCategory = category;
    }

    /**
     * Consumes the current keybind category and removes it.
     */
    public static KeyMapping.Category consume() {
        KeyMapping.Category value = pendingCategory;
        pendingCategory = null;
        return value;
    }

    /**
     * Finds the current keybind category, and does nothing if it was not found.
     */
    public static int findCategory(KeyMapping.Category keyCategory) {
        KeyMapping[] copy = Minecraft.getInstance().options.keyMappings.clone();
        Arrays.sort(copy);

        int row = 0;
        KeyMapping.Category currentCategory = null;

        for (KeyMapping mapping : copy) {
            if (!Objects.equals(currentCategory, mapping.getCategory())) {
                currentCategory = mapping.getCategory();

                if (Objects.equals(keyCategory, currentCategory)) {
                    return row;
                }

                row++;
            }

            row++;
        }

        return 0;
    }
}