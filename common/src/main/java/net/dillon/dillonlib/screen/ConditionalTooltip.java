package net.dillon.dillonlib.screen;

import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;

/**
 * Holds two tooltips based on the result of a boolean supplier.
 * @since 1.2
 * @see WidgetData
 */
public class ConditionalTooltip {
    private final BooleanSupplier condition;
    private final Component trueTooltip;
    private final Component falseTooltip;

    private ConditionalTooltip(
            BooleanSupplier condition,
            Component trueTooltip,
            Component falseTooltip
    ) {
        this.condition = condition;
        this.trueTooltip = trueTooltip;
        this.falseTooltip = falseTooltip;
    }

    /**
     * Uses a custom condition instead of the widget's active state.
     */
    public static ConditionalTooltip ofCondition(
            BooleanSupplier condition,
            Component trueTooltip,
            Component falseTooltip
    ) {
        return new ConditionalTooltip(
                condition,
                trueTooltip,
                falseTooltip
        );
    }

    /**
     * Uses the widget's active state as the condition.
     */
    public static ConditionalTooltip of(
            Component trueTooltip,
            Component falseTooltip
    ) {
        return ofCondition(
                null,
                trueTooltip,
                falseTooltip
        );
    }

    /**
     * Uses an empty tooltip.
     */
    public static ConditionalTooltip empty() {
        return of(
                Component.empty(),
                Component.empty()
        );
    }

    /**
     * Uses a constant active tooltip.
     */
    public static ConditionalTooltip ofActive(
            Component trueTooltip
    ) {
        return ofCondition(
                () -> true,
                trueTooltip,
                Component.empty()
        );
    }

    /**
     * Returns the tooltip based on the supplied condition.
     *
     * @param shouldBeActive the widget's active-state supplier, used when no custom condition was supplied
     */
    public Component getTooltip(boolean shouldBeActive) {
        BooleanSupplier condition = this.condition != null
                ? this.condition
                : () -> shouldBeActive;

        return condition.getAsBoolean()
                ? this.trueTooltip
                : this.falseTooltip;
    }

    /**
     * @return whether the tooltip is empty or null.
     */
    public boolean isEmptyOrNullTooltip(Component tooltip) {
        return tooltip == null
                || tooltip.equals(Component.empty())
                || tooltip.equals(Component.EMPTY);
    }
}