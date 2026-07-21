package net.dillon.dillonlib.factory.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * A default {@link CrossbowItem}, with comes with built-in rendering fixes and charging rules.
 * @since 1.0
 * @see net.dillon.dillonlib.mixin.client.fix.bow
 */
public class CrossbowFactory extends CrossbowItem {
    public static final ItemFactoryPredicate DEFAULT = stack -> stack.is(Items.CROSSBOW) || stack.getItem() instanceof CrossbowFactory;

    public CrossbowFactory(Properties properties) {
        super(properties);
    }

    /**
     * @return if this crossbow is charged.
     */
    public static boolean isCharged(ItemStack crossbowStack) {
        CompoundTag compoundTag = crossbowStack.getTag();
        return compoundTag != null && compoundTag.getBoolean("Charged");
    }
}