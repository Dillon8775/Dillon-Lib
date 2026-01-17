package net.dillonlib.util;

import net.dillonlib.annotation.PrivateUse;
import net.dillonlib.registry.DLR;
import net.dillonlib.registry.item.BowFactory;
import net.dillonlib.registry.item.CrossbowFactory;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.ItemStack;

@PrivateUse
public class ValidBowFactories {

    /**
     * @return if the stack is a valid bow factory.
     */
    public static boolean isValidFactory(ItemStack stack) {
        for (BowFactory bow : DLR.getBowFactories().keySet()) {
            if (stack.isOf(bow)) {
                return true;
            }
        }
        for (CrossbowFactory crossbow : DLR.getCrossbowFactories()) {
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
        for (CrossbowFactory crossbow : DLR.getCrossbowFactories()) {
            if (stack.isOf(crossbow)) {
                return true;
            }
        }
        return stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }
}