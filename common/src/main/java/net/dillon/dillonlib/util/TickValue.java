package net.dillon.dillonlib.util;

import net.dillon.dillonlib.client.component.ComponentBuilder;

import java.util.StringJoiner;

/**
 * A helper class for calculating ticks.
 *
 * @since 1.3
 */
public record TickValue(double value) {
    private static final int TICKS_PER_SECOND = 20;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;

    /**
     * @return the raw int value passed in, could be any value.
     */
    public int valueInt() {
        return (int) value();
    }

    /**
     * @return a new value as ticks assuming {@code value} is in milliseconds.
     */
    public int toTicksMs() {
        return (toMsInt() % 1000) / 10;
    }

    /**
     * @return a new value as ticks assuming {@code value} is in seconds.
     */
    public int toTicksSeconds() {
        return (int) (value * TICKS_PER_SECOND);
    }

    /**
     * @return a new value as ticks assuming {@code value} is in minutes.
     */
    public int toTicksMinutes() {
        return toTicksSeconds() * SECONDS_PER_MINUTE;
    }

    /**
     * @return a new value as ticks assuming {@code value} is in hours.
     */
    public int toTicksHours() {
        return toTicksMinutes() * MINUTES_PER_HOUR;
    }

    /**
     * @return a new value as ticks assuming {@code value} is in days.
     */
    public int toTicksDays() {
        return toTicksHours() * HOURS_PER_DAY;
    }

    /**
     * @return a new value as milliseconds assuming {@code value} are ticks.
     */
    public int toMsInt() {
        return (int) (value * 50);
    }

    /**
     * @return a new {@link Double} value as seconds assuming {@code value} are ticks.
     */
    public double toSeconds() {
        return value / TICKS_PER_SECOND;
    }

    /**
     * @return a new {@link Double} value as minutes assuming {@code value} are ticks.
     */
    public double toMinutes() {
        return toSeconds() / SECONDS_PER_MINUTE;
    }

    /**
     * @return a new {@link Double} value as hours assuming {@code value} are ticks.
     */
    public double toHours() {
        return toMinutes() / MINUTES_PER_HOUR;
    }

    /**
     * @return a new {@link Double} value as days assuming {@code value} are ticks.
     */
    public double toDays() {
        return toHours() / HOURS_PER_DAY;
    }

    /**
     * @return a new value as seconds assuming {@code value} are ticks.
     */
    public int toSecondsInt() {
        return (int) toSeconds();
    }

    /**
     * @return a new value as minutes assuming {@code value} are ticks.
     */
    public int toMinutesInt() {
        return (int) toMinutes();
    }

    /**
     * @return a new value as hours assuming {@code value} are ticks.
     */
    public int toHoursInt() {
        return (int) toHours();
    }

    /**
     * @return a new value as days assuming {@code value} are ticks.
     */
    public int toDaysInt() {
        return (int) toDays();
    }

    /**
     * @return a time-based formatted string assuming {@code value} are ticks.
     */
    public String formattedFromTick() {
        return new TimeFormatter(this).build();
    }

    /**
     * A builder class for creating formatted time text.
     */
    public static class TimeFormatter implements ComponentBuilder<String> {
        private final TickValue tickValue;
        private boolean includeSeconds = true;
        private boolean includeMilliseconds = false;

        public TimeFormatter(TickValue tickValue) {
            this.tickValue = tickValue;
        }

        public TimeFormatter includeSeconds(boolean includeSeconds) {
            this.includeSeconds = includeSeconds;
            return this;
        }

        public TimeFormatter includeMilliseconds(boolean includeMilliseconds) {
            this.includeMilliseconds = includeMilliseconds;
            return this;
        }

        @Override
        public String build() {
            int totalSeconds = tickValue.toSecondsInt();

            int days = totalSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY);
            int hours = (totalSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR)) % HOURS_PER_DAY;
            int minutes = (totalSeconds / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
            int seconds = totalSeconds % SECONDS_PER_MINUTE;
            int milliseconds = tickValue.toTicksMs();

            StringJoiner joiner = new StringJoiner(" ");

            if (days > 0) {
                joiner.add(days + "d");
            }

            if (hours > 0) {
                joiner.add(hours + "h");
            }

            if (minutes > 0) {
                joiner.add(minutes + "m");
            }

            if (includeSeconds && seconds > 0) {
                joiner.add(seconds + "s");
            }

            if (includeMilliseconds) {
                joiner.add(String.format("%02dms", milliseconds));
            }

            return joiner.length() == 0 ? "0s" : joiner.toString();
        }
    }
}