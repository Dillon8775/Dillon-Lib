package net.dillon.dillonlib.mixin.client.fix.bow;

import net.dillon.dillonlib.factory.item.CrossbowFactory;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public class FabricItemInHandRendererFix {

    @Redirect(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean redirectRenderFirstPersonItem(ItemStack stack, Object o) {
        return CrossbowFactory.DEFAULT.test(stack);
    }
}