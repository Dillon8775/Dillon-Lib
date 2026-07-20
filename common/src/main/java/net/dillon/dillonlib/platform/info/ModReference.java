package net.dillon.dillonlib.platform.info;

/**
 * Used to create {@code mod id references}.
 * @since 1.0
 * @param modId the mod identifier to reference when checking if a certain mod is loaded.
 */
public record ModReference(String modId) {
}