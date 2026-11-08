package net.dillon.dillonlib.annotation;

/**
 * Provides universal annotation for client and dedicated server classes and methods. This annotation does not actually split the sources, but rather an indicator to keep your client and common code separate to avoid faulty code.
 * @since 1.0
 * @see DillEnv
 */
public @interface Dill {
    /// Specifies the environment (or env) for the annotated class
    DillEnv env();
    /// Specifies the type for the annotated class. Default = universal because all classes used outside of this library should be annotated with universal
    DillType type() default DillType.UNIVERSAL;
}