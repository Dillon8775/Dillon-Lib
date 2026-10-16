package net.dillon.dillonlib.platform.info;

/**
 * Used to create {@code mod id references}.
 * @since 1.0
 */
public class ModReference {
    public static final String ANY = "*";
    private final String modId;
    private final String version;

    /**
     * @param modId the mod identifier to reference when checking if a certain mod is loaded.
     */
    private ModReference(final String modId, final String version) {
        this.modId = modId;
        this.version = version;
    }

    /**
     * Creates a new {@code ModReference}.
     */
    public static ModReference of(String modId) {
        return new ModReference(modId, ANY);
    }

    /**
     * Creates a new {@code ModReference} with a specific version number.
     */
    public static ModReference of(String modId, String version) {
        return new ModReference(modId, version);
    }

    /**
     * @return the mod's {@code mod id}.
     */
    public String id() {
        return this.modId;
    }

    /**
     * @return the mod's {@code version}.
     */
    public String version() {
        return this.version;
    }
}