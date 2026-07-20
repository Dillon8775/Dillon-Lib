package net.dillon.dillonlib.mixin.shear;

import net.dillon.dillonlib.factory.item.ShearsFactory;
import net.dillon.dillonlib.mixinplugin.PredicateSigned;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@PredicateSigned
@Mixin(MatchTool.class)
public class MatchToolFix {

    /**
     * Registers {@link ShearsFactory} to act as shears under certain predicates.
     */
    @Inject(method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z", at = @At("HEAD"), cancellable = true)
    private void dillonlib$test(LootContext context, CallbackInfoReturnable<Boolean> cir) {
        ItemInstance tool = context.getOptionalParameter(LootContextParams.TOOL);

        if (tool == null) {
            return;
        }

        if (tool.typeHolder().value() instanceof ShearsFactory) {
            MatchTool self = (MatchTool) (Object) this;

            if (self.predicate().isPresent() && self.predicate().get().items().isPresent()) {
                Holder<Item> vanillaShears = BuiltInRegistries.ITEM.wrapAsHolder(Items.SHEARS);

                if (self.predicate().get().items().get().contains(vanillaShears)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}