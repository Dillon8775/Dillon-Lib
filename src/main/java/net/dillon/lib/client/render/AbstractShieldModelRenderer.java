package net.dillon.lib.client.render;

import net.dillon.lib.annotation.PrivateUse;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Objects;
import java.util.Set;

/**
 * The renderer class for all {@code shield factories.}
 */
@PrivateUse
@Environment(EnvType.CLIENT)
public abstract class AbstractShieldModelRenderer implements SpecialModelRenderer<ComponentMap> {
    private final ShieldEntityModel shieldModel;

    public AbstractShieldModelRenderer(ShieldEntityModel model) {
        this.shieldModel = model;
    }

    /**
     * Copied over from {@link ShieldModelRenderer}.
     * <p>Creates a renderer for a {@code shield factory.}</p>
     */
    @Override
    public void render(
            @Nullable ComponentMap componentMap,
            ItemDisplayContext itemDisplayContext,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            int j,
            boolean bl
    ) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null
                ? (BannerPatternsComponent)componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? (DyeColor)componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl2 ? this.getBaseSprite() : this.getBaseNoPatternSprite();
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite()
                .getTextureSpecificVertexConsumer(
                        ItemRenderer.getItemGlintConsumer(
                                vertexConsumerProvider, this.shieldModel.getLayer(spriteIdentifier.getAtlasId()), itemDisplayContext == ItemDisplayContext.GUI, bl
                        )
                );
        this.shieldModel.getHandle().render(matrixStack, vertexConsumer, i, j);
        if (bl2) {
            BannerBlockEntityRenderer.renderCanvas(
                    matrixStack,
                    vertexConsumerProvider,
                    i,
                    j,
                    this.shieldModel.getPlate(),
                    spriteIdentifier,
                    false,
                    (DyeColor)Objects.requireNonNullElse(dyeColor, DyeColor.WHITE),
                    bannerPatternsComponent,
                    bl,
                    false
            );
        } else {
            this.shieldModel.getPlate().render(matrixStack, vertexConsumer, i, j);
        }

        matrixStack.pop();
    }

    @Override
    public @Nullable ComponentMap getData(ItemStack stack) {
        return stack.getImmutableComponents();
    }

    @Override
    public void collectVertices(Set<Vector3f> vertices) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.shieldModel.getRootPart().collectVertices(matrixStack, vertices);
    }

    protected abstract SpriteIdentifier getBaseSprite();
    protected abstract SpriteIdentifier getBaseNoPatternSprite();
}