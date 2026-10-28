package net.dillon.dillonlib.platform.info;

import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Stores menu button data.
 */
public class PlatformMenuButton {
    private final UpdatableSpriteButton menuButton;
    private final Consumer<SpriteIconButton> consumer;

    private PlatformMenuButton(UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        this.menuButton = menuButton;
        this.consumer = consumer;
    }

    /**
     * Creates a new {@code PlatformMenuButton}.
     */
    public static PlatformMenuButton of(UpdatableSpriteButton menuButton, Consumer<SpriteIconButton> consumer) {
        return new PlatformMenuButton(menuButton, consumer);
    }

    /**
     * Creates a new {@code PlatformMenuButton}, with an {@code empty consumer}.
     */
    public static PlatformMenuButton of(UpdatableSpriteButton menuButton) {
        return of(menuButton, spriteIconButton -> {});
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
                p -> p.button().getOrderName()
        ));
    }
}