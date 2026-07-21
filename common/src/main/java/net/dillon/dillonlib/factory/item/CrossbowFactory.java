package net.dillon.dillonlib.factory.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

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
    public static boolean isCharged(ItemStack itemStack) {
        ChargedProjectiles projectiles = itemStack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
        return !projectiles.isEmpty();
    }
}