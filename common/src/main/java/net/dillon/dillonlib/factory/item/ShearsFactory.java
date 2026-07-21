package net.dillon.dillonlib.factory.item;

import net.dillon.dillonlib.core.DillonLibEvents;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.mixin.shear.MatchToolFix;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;

/**
 * A default {@link ShearsItem}, with built-in mixin fixes for shearing entities, blocks, breaking certain blocks, and predicates.
 * @since 1.0
 * @see net.dillon.dillonlib.mixin.shear
 * @see MatchToolFix
 * @see DillonLibEvents
 */
public class ShearsFactory extends ShearsItem {
    public static final ItemFactoryPredicate DEFAULT = stack -> stack.is(Items.SHEARS) || stack.getItem() instanceof ShearsFactory;
    public final boolean registerDispenserBehavior;

    public ShearsFactory(Properties properties) {
        this(properties, true);
    }

    public ShearsFactory(Properties properties, boolean registerDispenserBehavior) {
        super(properties);
        this.registerDispenserBehavior = registerDispenserBehavior;
        Factories.SHEARS.add(this);
    }
}