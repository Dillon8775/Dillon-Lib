package net.dillon.dillonlib;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stores server-side data for a player's {@link UUID}, mainly used for syncing client-options with the server for player preferences.
 * @param <T> the type of data stored for the player's UUID.
 */
public class PlayerStorage<T> {
    private final Map<UUID, T> values = new ConcurrentHashMap<>();
    private final T defaultValue;

    public PlayerStorage(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

    /**
     * Sets the value for the player.
     */
    public void set(UUID playerUuid, T value) {
        values.put(playerUuid, value);
        onSet(playerUuid, value);
    }

    /**
     * Performs an action when data is set.
     */
    protected void onSet(UUID playerUuid, T value) {
    }

    /**
     * @return the value for the player.
     */
    public T get(UUID playerUuid) {
        return values.getOrDefault(playerUuid, defaultValue);
    }

    /**
     * Removes the value for the player.
     */
    public void remove(UUID playerUuid) {
        values.remove(playerUuid);
    }

    /**
     * @return if the player has this data.
     */
    public boolean contains(UUID playerUuid) {
        return values.containsKey(playerUuid);
    }
}