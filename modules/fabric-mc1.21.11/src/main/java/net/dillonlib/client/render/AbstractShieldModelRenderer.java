package net.dillonlib.client.render;

import net.dillonlib.annotation.PrivateUse;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Unit;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * The renderer class for all {@code shield factories.}
 */
@PrivateUse
@Environment(EnvType.CLIENT)
public abstract class AbstractShieldModelRenderer implements SpecialModelRenderer<ComponentMap> {
    private final SpriteHolder spriteHolder;
    private final ShieldEntityModel shieldModel;

    public AbstractShieldModelRenderer(SpriteHolder spriteHolder, ShieldEntityModel model) {
        this.spriteHolder = spriteHolder;
        this.shieldModel = model;
    }

    /**
     * Copied over from {@link ShieldModelRenderer}.
     * <p>Creates a renderer for a {@code shield factory.}</p>
     */
    @Override
    public void render(@Nullable ComponentMap componentMap, ItemDisplayContext itemDisplayContext, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int i, int j, boolean bl, int k) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null ? (BannerPatternsComponent)componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT) : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? (DyeColor)componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl2 ? this.getBaseSprite() : this.getBaseNoPatternSprite();
        orderedRenderCommandQueue.submitModelPart(this.shieldModel.getHandle(), matrixStack, this.shieldModel.getLayer(spriteIdentifier.getAtlasId()), i, j, this.spriteHolder.getSprite(spriteIdentifier), false, false, -1, (ModelCommandRenderer.CrumblingOverlayCommand)null, k);
        if (bl2) {
            BannerBlockEntityRenderer.renderCanvas(this.spriteHolder, matrixStack, orderedRenderCommandQueue, i, j, this.shieldModel, Unit.INSTANCE, spriteIdentifier, false, (DyeColor)Objects.requireNonNullElse(dyeColor, DyeColor.WHITE), bannerPatternsComponent, bl, (ModelCommandRenderer.CrumblingOverlayCommand)null, k);
        } else {
            orderedRenderCommandQueue.submitModelPart(this.shieldModel.getPlate(), matrixStack, this.shieldModel.getLayer(spriteIdentifier.getAtlasId()), i, j, this.spriteHolder.getSprite(spriteIdentifier), false, bl, -1, (ModelCommandRenderer.CrumblingOverlayCommand)null, k);
        }

        matrixStack.pop();
    }

    @Override
    public @Nullable ComponentMap getData(ItemStack stack) {
        return stack.getImmutableComponents();
    }

    @Override
    public void collectVertices(Consumer<Vector3fc> consumer) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.shieldModel.getRootPart().collectVertices(matrixStack, consumer);
    }

    protected abstract SpriteIdentifier getBaseSprite();
    protected abstract SpriteIdentifier getBaseNoPatternSprite();
}