package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixin.accessor.ItemPredicateAccessor;
import net.dillon.dillonlib.mixin.accessor.MatchToolAccessor;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@PredicateSigned
@Mixin(MatchTool.class)
public class MatchToolFix {

    /**
     * Registers {@link ShearsFactory} to act as shears under certain predicates.
     */
    @Inject(method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z", at = @At("HEAD"), cancellable = true)
    private void dillonlib$test(LootContext context, CallbackInfoReturnable<Boolean> cir) {
        ItemStack tool = context.getParam(LootContextParams.TOOL);

        if (tool == null) {
            return;
        }

        if (tool.getItem() instanceof ShearsFactory) {
            ItemPredicate predicate = ((MatchToolAccessor) (Object) this).getPredicate();
            Set<Item> items = ((ItemPredicateAccessor) (Object) predicate).getItems();

            if (items.contains(Items.SHEARS)) {
                cir.setReturnValue(true);
            }
        }
    }
}