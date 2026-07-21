package net.dillon.dillonlib.mixin.client.fix.bow;

import net.dillon.dillonlib.factory.item.CrossbowFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(PlayerRenderer.class)
public class PlayerRendererFix {

    /**
     * Fixes some incorrect rendering with crossbows.
     */
    @Redirect(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean getArmPoseFactory(ItemStack stack, Item item) {
        return CrossbowFactory.DEFAULT.test(stack);
    }
}