package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.Comparator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Stores menu button data.
 */
@Dill(env = DillEnv.CLIENT)
public class PlatformMenuButton {
    private final UpdatableSpriteButton menuButton;
    private final BooleanSupplier visible;
    private final Consumer<SpriteIconButton> consumer;

    private PlatformMenuButton(UpdatableSpriteButton menuButton, BooleanSupplier visible, Consumer<SpriteIconButton> consumer) {
        this.visible = visible;
        this.menuButton = menuButton;
        this.consumer = consumer;
    }

    /**
     * Creates a new builder.
     */
    public static Builder builder(UpdatableSpriteButton menuButton) {
        return new Builder(menuButton);
    }

    /**
     * @return the button's visibility.
     */
    public BooleanSupplier visible() {
        return this.visible;
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

    /**
     * A builder class for creating {@link PlatformMenuButton}s.
     */
    public static class Builder implements ComponentBuilder<PlatformMenuButton> {
        private final UpdatableSpriteButton menuButton;
        private BooleanSupplier visible = () -> true;
        private Consumer<SpriteIconButton> consumer = spriteIconButton -> {};

        public Builder(UpdatableSpriteButton menuButton) {
            this.menuButton = menuButton;
        }

        public Builder visible(BooleanSupplier visible) {
            this.visible = visible;
            return this;
        }

        public Builder consumer(Consumer<SpriteIconButton> consumer) {
            this.consumer = consumer;
            return this;
        }

        @Override
        public PlatformMenuButton build() {
            return new PlatformMenuButton(menuButton, visible, consumer);
        }
    }
}