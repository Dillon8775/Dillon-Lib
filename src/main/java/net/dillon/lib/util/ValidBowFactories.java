package net.dillon.lib.util;

import net.dillon.lib.annotation.PrivateUse;
import net.dillon.lib.registry.DillonsRegistry;
import net.dillon.lib.registry.item.BowFactory;
import net.dillon.lib.registry.item.CrossbowFactory;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.ItemStack;

@PrivateUse
public class ValidBowFactories {

    /**
     * @return if the stack is a valid bow factory.
     */
    public static boolean isValidFactory(ItemStack stack) {
        for (BowFactory bow : DillonsRegistry.getBowFactories().keySet()) {
            if (stack.isOf(bow)) {
                return true;
            }
        }
        for (CrossbowFactory crossbow : DillonsRegistry.getCrossbowFactories()) {
            if (stack.isOf(crossbow)) {
                return true;
            }
        }
        return stack.isIn(ConventionalItemTags.BOW_TOOLS) || stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }

    /**
     * @return if the stack is a valid crossbow factory.
     */

    public static boolean isValidCrossbow(ItemStack stack) {
        for (CrossbowFactory crossbow : DillonsRegistry.getCrossbowFactories()) {
            if (stack.isOf(crossbow)) {
                return true;
            }
        }
        return stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }
}