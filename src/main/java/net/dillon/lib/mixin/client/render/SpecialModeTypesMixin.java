package net.dillon.lib.mixin.client.render;

import com.mojang.serialization.MapCodec;
import net.dillon.lib.client.render.RendererRegistry;
import net.dillon.lib.registry.DillonsRegistry;
import net.dillon.lib.registry.data.ShieldData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SpecialModelTypes.class)
public class SpecialModeTypesMixin {
    @Shadow @Final
    public static Codecs.IdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER;

    /**
     * Fixes a bug where sometimes, shield factory renderers don't register correctly.
     */
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerShieldFactoryRenderers(CallbackInfo ci) {
        for (ShieldData data : DillonsRegistry.getShieldFactories()) {
            String id = data.identifier().getPath();
            MapCodec<? extends SpecialModelRenderer.Unbaked> specialModeRendererCodec =
                    RendererRegistry.createUnbakedShieldRenderer(
                            new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base")),
                                    new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base_no_pattern")))
                            .getCodec();
            ID_MAPPER.put(data.identifier(), specialModeRendererCodec);
        }
    }
}