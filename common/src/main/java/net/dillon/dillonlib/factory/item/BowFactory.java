package net.dillon.dillonlib.factory.item;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Items;

/**
 * A default {@link BowItem}, with comes with built-in fov effect changers and correct bow use.
 * @since 1.0
 * @see net.dillon.dillonlib.mixin.client.fix.bow
 */
public class BowFactory extends BowItem {
    public static final ItemFactoryPredicate DEFAULT = stack -> stack.is(Items.BOW) || stack.getItem() instanceof BowFactory;
    public static final ItemFactoryPredicate NEOFORGE = stack -> stack.getItem() instanceof BowItem;

    public BowFactory(Properties properties) {
        super(properties);
    }
}