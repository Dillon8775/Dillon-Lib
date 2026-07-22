package net.dillon.dillonlib.mixin.client;

import com.mojang.serialization.Codec;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.event.IncreasedBrightnessSliderCallback;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(OptionInstance.class)
public abstract class FabricOptionInstanceMixin {
    @Shadow @Final
    Component caption;
    @Shadow @Final @Mutable
    Function<Double, Component> toString;
    @Shadow @Final @Mutable
    private OptionInstance.ValueSet<Double> values;
    @Shadow @Final @Mutable
    private Codec<Double> codec;
    @Shadow @Final @Mutable
    private OptionInstance.ValueUpdateListener<? super Double> onValueUpdate;

    @Inject(at = @At("RETURN"), method = "<init>*", remap = false)
    protected void init(CallbackInfo info) {
        if (this.caption.getContents() instanceof TranslatableContents translatableContents && translatableContents.getKey().equals("options.gamma")) {
            this.onValueUpdate = DillonLibClient::onValueUpdate;
            this.toString = DillonLibClient::toComponentString;
            this.values = IncreasedBrightnessSliderCallback.INSTANCE;
            this.codec = this.values.codec();
        }
    }
}