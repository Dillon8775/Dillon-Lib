package net.dillon.dillonlib.util;

import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraft.util.RandomSource;

/**
 * Different formulas and math expressions.
 * @since 1.0
 */
public class Arithmetics {
    private static final RandomSource RANDOM = RandomSource.create();

    /**
     * Converts seconds to ticks (sas = seconds as ticks)
     */
    public static int sas(int secondsAsTicks) {
        try {
            int testSeconds = 0;
            while (testSeconds < 525600) {
                if (secondsAsTicks == testSeconds) {
                    throw new NumberFormatException();
                }
                testSeconds += 60;
            }
            return secondsAsTicks * 20;
        } catch (NumberFormatException o) {
            PlatformGetter.getDillonLibPlatform().logger().error("Use method minutesInTicks(int) if you're inputting an exact minute.");
            o.printStackTrace();
            return mas(secondsAsTicks / 60);
        }
    }

    /**
     * Converts minutes to ticks (mas = minutes as ticks).
     */
    public static int mas(int minutesAsTicks) {
        return (minutesAsTicks * 60) * 20;
    }

    /**
     * Rounds the value to the nearest tenths place.
     */
    public static double round(double d) {
        return Math.round(d * 10.0) / 10.0;
    }

    /**
     * Rounds a value to the nearest hundredths place.
     */
    public static double roundBig(double d) {
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