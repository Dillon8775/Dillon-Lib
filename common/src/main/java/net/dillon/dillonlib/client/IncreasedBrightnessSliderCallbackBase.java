package net.dillon.dillonlib.client;

import com.mojang.serialization.Codec;

import java.util.Optional;

/**
 * A base callback for implementing full bright into the game.
 */
public class IncreasedBrightnessSliderCallbackBase {
    public static final double min = 0.0D;
    public static final double max = 12.0D;

    /**
     * @return the valid gamma value, for the minimum and maximum value.
     */
    public static Optional<Double> validateValue(Double value) {
        if (value >= min && value <= max) {
            return Optional.of(value);
        }

        return Optional.empty();
    }

    /**
     * @return the double-range {@link Codec} for the gamma value.
     */
    public static Codec<Double> codec() {
        return Codec.doubleRange(min, max);
    }

    /**
     * @return the new gamma's value.
     */
    public static double toSliderValue(Double value) {
        return value / max;
    }

    /**
     * @return the slider value from the gamma value.
     */
    public static Double fromSliderValue(double sliderValue) {
        double value = sliderValue * max;

        return Math.round(value * 10.0) / 10.0;
    }
}