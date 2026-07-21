package net.dillon.dillonlib.mixinplugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicates that a mixin is {@code signed}, which means that the mixin has the possibility of being disabled due to a {@link PredicateEntry}.
 * @since 1.0
 * @see PredicateEntry
 */
@Target(ElementType.TYPE)
public @interface PredicateSigned {
}