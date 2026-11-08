package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

/**
 * A builder class for creating a {@code done} button.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class DoneButtonBuilder implements ComponentBuilder<AbstractWidget>, TooltipBuilder<DoneButtonBuilder> {
    private final Screen screen;
    private Supplier<Component> tooltip = Component::empty;
    private int width = 200;
    private Component text = Component.translatable("gui.done");

    private DoneButtonBuilder(Screen screen) {
        this.screen = screen;
    }

    public static DoneButtonBuilder builder(Screen screen) {
        return new DoneButtonBuilder(screen);
    }

    @Override
    public DoneButtonBuilder tooltip(Component tooltip) {
        return tooltip(() -> tooltip);
    }

    @Override
    public DoneButtonBuilder tooltip(Supplier<Component> tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public DoneButtonBuilder width(int width) {
        this.width = width;
        return this;
    }

    public DoneButtonBuilder text(Component text) {
        this.text = text;
        return this;
    }

    @Override
    public AbstractWidget build() {
        return Button.builder(
                        text,
                button -> screen.onClose())
                .width(width)
                .tooltip(Tooltip.create(tooltip.get()))
                .build();
    }
}