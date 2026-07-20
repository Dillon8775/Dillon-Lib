package net.dillon.dillonlib.annotation;

/**
 * Annotations for a class, method or field, on what environment the object should be run on. These do not actually do anything, just for simple universal annotation.
 * @since 1.0
 * @see Dill
 */
public enum DillType {
    CLIENT,
    DEDICATED_SERVER,
    COMMON, // Should only be used if mod is generally a client/server side mod
}