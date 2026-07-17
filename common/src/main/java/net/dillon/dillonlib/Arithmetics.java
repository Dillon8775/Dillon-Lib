package net.dillon.dillonlib;

import net.dillon.dillonlib.platform.Statics;
import net.minecraft.util.RandomSource;

/**
 * Different formulas and math expressions commonly used in my mods.
 */
public class Arithmetics {
    private static final RandomSource RANDOM = RandomSource.create();

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

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
            Statics.error("Use method minutesInTicks(int) if you're inputting an exact minute.");
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