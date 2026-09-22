package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.components.AbstractWidget;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Holds data for a widget.
 * @since 1.2
 * @see ScreenBuilder
 * @see BasicDillonLibScreen
 * @see DillonLibMenuScreen
 */
@Dill(DillType.CLIENT)
public class WidgetData {
    private final AbstractWidget widget;
    private final BooleanSupplier shouldBeActive;
    private final Supplier<ConditionalTooltip> conditionalTooltip;
    private final IntSupplier x;
    private final IntSupplier y;
    public static final int UNMODIFIED = -1;

    private WidgetData(AbstractWidget widget, BooleanSupplier shouldBeActive, Supplier<ConditionalTooltip> conditionalTooltip, IntSupplier x, IntSupplier y) {
        this.widget = widget;
        this.shouldBeActive = shouldBeActive;
        this.conditionalTooltip = conditionalTooltip;
        this.x = x;
        this.y = y;
    }

    /**
     * Creates new {@code Widget Data}.
     */
    public static WidgetData of(AbstractWidget widget, BooleanSupplier shouldBeActive, Supplier<ConditionalTooltip> conditionalTooltip, IntSupplier x, IntSupplier y) {
        return new WidgetData(
                widget,
                shouldBeActive,
                conditionalTooltip,
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
    public ConditionalTooltip getConditionalTooltip() {
        return this.conditionalTooltip.get();
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