package net.dillon.dillonlib.platform.info;

import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Stores menu button data.
 */
public class PlatformMenuButton {
    private final boolean titleCondition;
    private final boolean pauseCondition;
    private final UpdatableSpriteButton menuButton;
    private final Consumer<SpriteIconButton> consumer;
    public static final int TITLE_SCREEN_BUTTON_CAP = 16;
    public static final int PAUSE_SCREEN_BUTTON_CAP = 7;

    private PlatformMenuButton(boolean titleCondition, boolean pauseCondition, UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        this.titleCondition = titleCondition;
        this.pauseCondition = pauseCondition;
        this.menuButton = menuButton;
        this.consumer = consumer;
    }

    /**
     * Creates a new {@code PlatformMenuButton}.
     */
    public static PlatformMenuButton of(boolean titleCondition, boolean pauseCondition, UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        return new PlatformMenuButton(titleCondition, pauseCondition, menuButton, consumer);
    }

    /**
     * Creates a new {@code PlatformMenuButton}, with an {@code empty consumer}.
     */
    public static PlatformMenuButton ofEmpty(boolean titleCondition, boolean pauseCondition, UpdatableSpriteButton menuButton) {
        return of(titleCondition, pauseCondition, menuButton, spriteIconButton -> {});
    }

    /**
     * Creates a new {@code PlatformMenuButton}, only possibly rendering on the {@code title screen}, with an {@code empty consumer}.
     */
    public static PlatformMenuButton titleOnlyEmpty(boolean titleCondition, UpdatableSpriteButton menuButton) {
        return titleOnly(titleCondition, menuButton, spriteIconButton -> {});
    }

    /**
     * Creates a new {@code PlatformMenuButton}, only possibly rendering on the {@code pause screen}, with an {@code empty consumer}.
     */
    public static PlatformMenuButton pauseOnlyEmpty(boolean pauseCondition, UpdatableSpriteButton menuButton) {
        return pauseOnly(pauseCondition, menuButton, spriteIconButton -> {});
    }

    /**
     * Creates a new {@code PlatformMenuButton}, only possibly rendering on the {@code title screen}.
     */
    public static PlatformMenuButton titleOnly(boolean titleCondition, UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        return of(titleCondition, false, menuButton, consumer);
    }

    /**
     * Creates a new {@code PlatformMenuButton}, only possibly rendering on the {@code pause screen}.
     */
    public static PlatformMenuButton pauseOnly(boolean pauseCondition, UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        return of(false, pauseCondition, menuButton, consumer);
    }

    /**
     * @return if the button can render on the {@code Title screen}.
     */
    public boolean isOnTitle() {
        return this.titleCondition;
    }

    /**
     * @return if the button can render on the {@code Pause screen}.
     */
    public boolean isOnPause() {
        return this.pauseCondition;
    }

    /**
     * @return the {@code menu button}.
     */
    public UpdatableSpriteButton button() {
        return this.menuButton;
    }

    /**
     * @return the {@code consumer} for the menu button.
     */
    public Consumer<SpriteIconButton> consumer() {
        return this.consumer;
    }

    /**
     * Sorts platform menu buttons in a correct order.
     */
    public static void sortButtons(List<PlatformMenuButton> buttons) {
        buttons.sort(Comparator.comparing(
                p -> p.button().getName()
        ));
    }
}