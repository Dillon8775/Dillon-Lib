package net.dillon.lib.mixin.main.fix;

import net.dillon.lib.registry.DillonsRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowGolemEntity.class)
public class SnowGolemEntityMixin {

    /**
     * Fixes {@code shear factories} not working on snow golems.
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectSnowGolemEntityUseWithShears(ItemStack stack, Item item) {
        for (ShearsItem shears : DillonsRegistry.getShearsFactories()) {
            if (stack.isOf(shears)) {
                return true;
            }
        }
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}