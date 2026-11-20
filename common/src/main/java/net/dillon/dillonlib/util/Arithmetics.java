package net.dillon.dillonlib.util;

import net.minecraft.util.RandomSource;

/**
 * Different formulas and math expressions.
 * @since 1.0
 */
public class Arithmetics {
    private static final RandomSource RANDOM = RandomSource.create();

    /**
     * Rounds the value to the nearest tenths place.
     */
    public static double roundToTenths(double d) {
        return Math.round(d * 10.0) / 10.0;
    }

    /**
     * Rounds a value to the nearest hundredths place.
     */
    public static double roundToHundredths(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    /**
     * @return {@code true} with the specified percentage chance (0-100).
     */
    public static boolean percentChance(float chance) {
        return RANDOM.nextFloat() * 100.0F < chance;
    }

    /**
     * @return a random float, with a minimum and maximum value.
     */
    public static float rangedPercentChance(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }

    /**
     * @return a random int, with a minimum and maximum value.
     */
    public static int rangedPercentChance(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}