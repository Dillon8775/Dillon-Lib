package net.dillon.dillonlib.annotation;

/**
 * Provides universal annotation for client and dedicated server classes and methods. This annotation does not actually split the sources, but rather an indicator to keep your client and common code separate to avoid faulty code.
 * @since 1.0
 * @see DillType
 */
public @interface Dill {
    DillType value();
}