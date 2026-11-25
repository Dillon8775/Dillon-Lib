package net.dillon.dillonlib.util;

/**
 * Stores a task to be executed after a certain amount of time.
 * @since 1.0
 */
public class ScheduledTask {
    public int ticks;
    public final Runnable runnable;

    public ScheduledTask(int ticks, Runnable runnable) {
        this.ticks = ticks;
        this.runnable = runnable;
    }
}