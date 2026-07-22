package net.dillon.dillonlib.client;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.util.BaseOptions;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Creates modern-like list widgets, with different texts and options.
 * @since 1.0
 * @see ModernListWidget
 */
@Dill(DillType.CLIENT)
public class ModernWidgetOptions {
    public static final OptionInstance.CaptionBasedToString<Boolean> ON_OFF_TEXT = (component, bl) -> bl
            ? Texts.ON
            : Texts.OFF;
    public static final OptionInstance.CaptionBasedToString<Boolean> YES_NO_TEXT = (component, bl) -> bl
            ? Texts.YES
            : Texts.NO;

    /**
     * Creates an option widget.
     */
    private static AbstractWidget createOption(OptionInstance<?> instance) {
        return createOption(instance, 0, 0, 0);
    }

    /**
     * Creates an option widget with a custom x, y and width value.
     */
    private static AbstractWidget createOption(OptionInstance<?> instance, int x, int y, int width) {
        return instance.createButton(Minecraft.getInstance().options, x, y, width);
    }

    /**
     * @return a simple boolean {@link OptionInstance}.
     */
    public static <T> OptionInstance<Boolean> createSimpleBooleanOption(
            String translation,
            boolean toggleText,
            boolean currentValue,
            BaseOptions<T> instance,
            BiConsumer<T, Boolean> consumer,
            Object... obj) {
        return OptionInstance.createBoolean(
                translation,
                OptionInstance.cachedConstantTooltip(
                        Component.translatable(translation + ".tooltip", obj)
                ),
                toggleText ? ON_OFF_TEXT : YES_NO_TEXT,
                currentValue,
                value -> instance.update(options -> consumer.accept(options, value))
        );
    }

    /**
     * @return an integer option {@link OptionInstance}.
     */
    public static <T> OptionInstance<Integer> createSimpleIntegerOption(
            String translation,
            BiFunction<Component, Integer, Component> display,
            OptionInstance.IntRange factory,
            int currentValue,
            BaseOptions<T> instance,
            BiConsumer<T, Integer> consumer
    ) {
        return new OptionInstance<>(
                translation,
                OptionInstance.cachedConstantTooltip(Component.translatable(translation + ".tooltip")),
                display::apply,
                factory,
                currentValue,
                value -> instance.update(options -> consumer.accept(options, value))
        );
    }

    /**
     * @return a special double {@link OptionInstance}.
     */
    public static <T> OptionInstance<Double> createDoubleOption(
            String translation,
            String displayText,
            double min,
            double max,
            double step,
            double currentValue,
            BaseOptions<T> instance,
            BiConsumer<T, Double> consumer
    ) {
        return new OptionInstance<>(translation,
                OptionInstance.cachedConstantTooltip(Component.translatable(translation + ".tooltip")),
                (optionText, value) -> Options.genericValueLabel(optionText, Component.literal(value + displayText)),
                OptionInstance.UnitDouble.INSTANCE.xmap(
                        slider -> {
                            double value = min + slider * (max - min);
                            return Math.round(value * step) / step;
                        },
                        value -> (value - min) / (max - min)
                ),
                currentValue,
                value -> instance.update(options -> consumer.accept(options, value)));
    }
}