package net.dillon.dillonlib.platform.info;

/**
 * Used to create {@code mod id references}.
 * @since 1.0
 */
public class ModReference {
    private final String modId;

    /**
     * @param modId the mod identifier to reference when checking if a certain mod is loaded.
     */
    private ModReference(final String modId) {
        this.modId = modId;
    }

    /**
     * Creates a new {@code ModReference}.
     */
    public static ModReference of(String modId) {
        return new ModReference(modId);
    }

    /**
     * @return the mod's {@code mod id}.
     */
    public String id() {
        return this.modId;
    }
}