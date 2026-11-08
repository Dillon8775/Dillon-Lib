package net.dillon.dillonlib.annotation;

/**
 * Annotations for a class, method or field, on what environment the object should be run on. These do not actually do anything, just for simple universal annotation.
 * @since 1.0
 * @see Dill
 */
public enum DillEnv {
    /// Specifies that a class only belongs to the client-side (doesn't interact with server unless packet is sent)
    CLIENT,
    /// Specifies that a class only belongs to the server-side (doesn't interact with client unless packet is sent)
    DEDICATED_SERVER,
    /// Specifies that a class belongs to both environments. Should only be used if mod is generally a client/server side mod
    COMMON
}