package net.dillon.dillonlib.factory.item;

import net.dillon.dillonlib.core.DillonLibEvents;
import net.dillon.dillonlib.factory.Factories;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;

/**
 * A default {@link Item}, with built-in mixin fixes for ignitables like tnt, sulfur cubes, etc.
 * @since 1.0
 * @see net.dillon.dillonlib.mixin.ignitable
 * @see DillonLibEvents
 */
public class IgnitableFactory extends Item {
    public static final ItemFactoryPredicate THIS_OR_STEEL = stack -> stack.getItem() instanceof IgnitableFactory || stack.getItem() instanceof IgnitableFactory.FlintAndSteel;

    public IgnitableFactory(Properties properties) {
        super(properties);
    }

    /**
     * A flint and steel version of an {@link IgnitableFactory} (not an instance of it), but is treated as an ignitable.
     */
    public static class FlintAndSteel extends FlintAndSteelItem {
        public final boolean registerDispenserBehavior;

        public FlintAndSteel(Properties properties) {
            this(properties, true);
        }

        public FlintAndSteel(Properties properties, boolean registerDispenserBehavior) {
            super(properties);
            this.registerDispenserBehavior = registerDispenserBehavior;
            Factories.FLINT_AND_STEELS.add(this);
        }
    }
}