package net.dillon.dillonlib.annotation;

/**
 * Provides universal annotation for client and dedicated server classes and methods.
 */
public @interface Dill {
    DillType value();
}