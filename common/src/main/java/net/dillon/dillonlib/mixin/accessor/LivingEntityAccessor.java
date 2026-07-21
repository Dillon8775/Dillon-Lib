package net.dillon.dillonlib.mixin.accessor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accesses the {@code useItem} variable in the player class.
 * @since 1.0 (1.21.1 and below only)
 */
@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Accessor("useItem")
    void setUseItem(ItemStack stack);
}