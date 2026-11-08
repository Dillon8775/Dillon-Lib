package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;

/**
 * A representation of a basic button builder.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public interface ComponentBuilder<T> {
    T build();
}