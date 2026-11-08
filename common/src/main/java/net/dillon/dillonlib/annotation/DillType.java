package net.dillon.dillonlib.annotation;

/**
 * Annotations for a class, method or field on if the class is built-in or not.
 * @since 1.3
 * @see Dill
 */
public enum DillType {
    /// Built-in to DillonLib, specifically for DillonLib and should not be used otherwise
    CORE,
    /// Can be extended or used elsewhere
    UNIVERSAL
}