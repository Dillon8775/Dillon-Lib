package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;

import static net.dillon.dillonlib.task.ClientTasks.getFont;

/**
 * A common interface for creating basic screens.
 *
 * @since 1.2
 * @see BasicDillonLibScreen
 * @see DillonLibMenuScreen
 */
@Dill(DillType.CLIENT)
public interface DillonLibScreen {

    /**
     * All widget data for a screen.
     */
    List<WidgetData> widgetData();

    /**
     * Override this method to add your widgets to the screen.
     */
    void widgets();

    /**
     * Creates a widget with custom active state, tooltip, and position.
     *
     * @param widget the widget
     * @param shouldBeActive determines whether the widget is active
     * @param tooltip supplies the widget's conditional tooltip
     * @param x the widget's x position
     * @param y the widget's y position
     * @return the supplied widget
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            BooleanSupplier shouldBeActive,
            Function<Boolean, Component> tooltip,
            IntSupplier x,
            IntSupplier y
    ) {
        widget.active = shouldBeActive.getAsBoolean();
        widgetData().add(WidgetData.of(
                widget,
                shouldBeActive,
                tooltip,
                x,
                y
        ));

        return widget;
    }

    /**
     * Creates a widget with custom active state and tooltip.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            BooleanSupplier shouldBeActive,
            Function<Boolean, Component> tooltip
    ) {
        return createWidget(
                widget,
                shouldBeActive,
                tooltip,
                () -> WidgetData.UNMODIFIED,
                () -> WidgetData.UNMODIFIED
        );
    }

    /**
     * Creates a widget with custom active state and no tooltip.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            BooleanSupplier shouldBeActive
    ) {
        return createWidget(
                widget,
                shouldBeActive,
                isActive -> Component.empty()
        );
    }

    /**
     * Creates an always-active widget with a tooltip.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            Component tooltip
    ) {
        return createWidget(
                widget,
                () -> true,
                isActive -> tooltip
        );
    }

    /**
     * Creates an always-active widget with a conditional tooltip.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            Function<Boolean, Component> tooltip
    ) {
        return createWidget(
                widget,
                () -> true,
                tooltip
        );
    }

    /**
     * Creates an always-active widget with no tooltip.
     */
    default AbstractWidget createWidget(AbstractWidget widget) {
        return createWidget(
                widget,
                () -> true
        );
    }

    /**
     * Creates a widget with custom active state and position.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            BooleanSupplier shouldBeActive,
            IntSupplier x,
            IntSupplier y
    ) {
        return createWidget(
                widget,
                shouldBeActive,
                isActive -> Component.empty(),
                x,
                y
        );
    }

    /**
     * Creates an always-active widget with a custom tooltip and position.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            Function<Boolean, Component> tooltip,
            IntSupplier x,
            IntSupplier y
    ) {
        return createWidget(
                widget,
                () -> true,
                tooltip,
                x,
                y
        );
    }

    /**
     * Creates an always-active widget with a custom position.
     */
    default AbstractWidget createWidget(
            AbstractWidget widget,
            IntSupplier x,
            IntSupplier y
    ) {
        return createWidget(
                widget,
                () -> true,
                isActive -> Component.empty(),
                x,
                y
        );
    }

    /**
     * Draws all widget data.
     */
    default void drawWidgetData(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY
    ) {
        for (WidgetData data : widgetData()) {
            AbstractWidget widget = data.getWidget();

            if (widget == null) {
                continue;
            }

            setWidgetPositions(data);

            widget.active = data.shouldBeActive().getAsBoolean();

            Component tooltip = data.getTooltip();

            if (widget.isHovered()) {
                graphics.setTooltipForNextFrame(
                        getFont(),
                        getFont().split(tooltip, 200),
                        mouseX,
                        mouseY
                );
            }
        }
    }

    /**
     * Sets widget positions.
     */
    default void setWidgetPositions(WidgetData data) {
        AbstractWidget widget = data.getWidget();

        int x = data.getX();
        if (x != WidgetData.UNMODIFIED) {
            widget.setX(x);
        }

        int y = data.getY();
        if (y != WidgetData.UNMODIFIED) {
            widget.setY(y);
        }
    }
}