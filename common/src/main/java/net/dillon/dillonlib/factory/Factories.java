package net.dillon.dillonlib.factory;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.factory.item.ShieldFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores different factories for Minecraft items and blocks, to register most of the things needed for that special item with one single method.
 * <p>See {@link net.dillon.dillonlib.factory.item} for more item-specific factories.</p>
 * @since 1.0
 * @see net.dillon.dillonlib.mixin
 */
public class Factories {
    public static final Set<ShearsFactory> SHEARS = new HashSet<>();
    public static final Set<IgnitableFactory.FlintAndSteel> FLINT_AND_STEELS = new HashSet<>();
    public static final Map<ShieldFactory, Integer> SHIELDS = new HashMap<>();

    /**
     * Initializes this class.
     */
    public static void i_() {
    }
}