package net.dillon.dillonlib.client.impl;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.screen.DillonLibMenuScreen;
import net.dillon.dillonlib.client.screen.DillonLibScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;

/**
 * Holds data for a widget.
 * @since 1.2
 * @see DillonLibScreen
 * @see DillonLibMenuScreen
 */
@Dill(env = DillEnv.CLIENT)
public class WidgetData {
    private final AbstractWidget widget;
    private final BooleanSupplier shouldBeActive;
    private final Function<Boolean, Component> tooltip;
    private final IntSupplier x;
    private final IntSupplier y;
    public static final int UNMODIFIED = -1;

    private WidgetData(AbstractWidget widget, BooleanSupplier shouldBeActive, Function<Boolean, Component> tooltip, IntSupplier x, IntSupplier y) {
        this.widget = widget;
        this.shouldBeActive = shouldBeActive;
        this.tooltip = tooltip;
        this.x = x;
        this.y = y;
    }

    /**
     * Creates new {@code Widget Data}.
     */
    public static WidgetData of(AbstractWidget widget, BooleanSupplier shouldBeActive, Function<Boolean, Component> tooltip, IntSupplier x, IntSupplier y) {
        return new WidgetData(
                widget,
                shouldBeActive,
                tooltip,
                x,
                y
        );
    }

    /**
     * @return the widget.
     */
    public AbstractWidget getWidget() {
        return this.widget;
    }

    /**
     * @return if the {@code width} should be active.
     */
    public BooleanSupplier shouldBeActive() {
        return this.shouldBeActive;
    }

    /**
     * @return the conditional tooltip.
     */
    public Component getTooltip() {
        return this.tooltip.apply(this.shouldBeActive.getAsBoolean());
    }

    /**
     * @return the x pos for this widget.
     */
    public int getX() {
        return this.x.getAsInt();
    }

    /**
     * @return the y pos for this widget.
     */
    public int getY() {
        return this.y.getAsInt();
    }
}