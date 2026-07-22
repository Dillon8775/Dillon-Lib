package net.dillon.dillonlib.mixin.client;

import net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder;
import net.dillon.dillonlib.client.IncreasedBrightnessSliderCallbackBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SodiumConfigBuilder.class)
public class FabricSodiumConfigBuilderMixin {

    /**
     * Modifies {@code Sodium's} gamma options so that the correct brightness value can be applied with the sodium mod.
     */
    @ModifyArgs(method = "buildGeneralPage", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;setRange(III)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;"))
    private void modifyGammaRange(Args args) {
        int var1 = args.get(0);
        int var2 = args.get(1);
        int var3 = args.get(2);
        // Match sodium's builder for the gamma option
        if (var1 == 0 && var2 == 100 && var3 == 1) {
            args.set(1, (int)(IncreasedBrightnessSliderCallbackBase.max * 100)); // Set minimum and maximum value
            args.set(2, 50); // Set binding
        }
    }
}