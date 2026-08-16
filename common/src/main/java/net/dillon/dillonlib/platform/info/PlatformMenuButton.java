package net.dillon.dillonlib.platform.info;

import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Stores menu button data.
 */
public record PlatformMenuButton(boolean titleCondition, boolean pauseCondition, UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
    public static final int TITLE_SCREEN_BUTTON_CAP = 16;
    public static final int PAUSE_SCREEN_BUTTON_CAP = 7;

    /**
     * Sorts platform menu buttons in a correct order.
     */
    public static void sortButtons(List<PlatformMenuButton> buttons) {
        buttons.sort(Comparator.comparing(
                data -> data.menuButton().getName()
        ));
    }
}