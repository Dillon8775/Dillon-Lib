package net.dillon.dillonlib.mixin.client.fix.bow;

import net.dillon.dillonlib.factory.item.BowFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@PredicateSigned
@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerFix {

    /**
     * Makes default {@link BowFactory}s work with FOV bow pullback.
     */
    @Redirect(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectToConventionalTag(ItemStack heldItem, Object o) {
        return PlatformGetter.getDillonLibPlatform().platformName().neoforge()
                ? BowFactory.NEOFORGE.test(heldItem) : BowFactory.DEFAULT.test(heldItem);
    }
}