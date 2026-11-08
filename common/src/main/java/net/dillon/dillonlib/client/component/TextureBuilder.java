package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;

/**
 * A builder for drawing textures.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class TextureBuilder {
    private final GuiGraphicsExtractor graphics;
    private final Identifier texture;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private float alpha = 1.0F;

    private TextureBuilder(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height) {
        this.graphics = graphics;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static TextureBuilder create(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height) {
        return new TextureBuilder(graphics, texture, x, y, width, height);
    }

    public static TextureBuilder createLargeOnButton(GuiGraphicsExtractor graphics, Identifier texture, Button button) {
        return create(graphics, texture, button.getX() + 1, button.getY() + 1, 18, 18);
    }

    public static TextureBuilder createSmallOnButton(GuiGraphicsExtractor graphics, Identifier texture, Button button) {
        return create(graphics, texture, button.getX() + 2, button.getY() + 2, 16, 16);
    }

    public TextureBuilder setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public void buildAndBlit(boolean sprite) {
        if (sprite) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, texture, x, y, width, height, ARGB.color(alpha, CommonColors.WHITE));
        } else {
            graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, width, height, width, height, ARGB.color(alpha, CommonColors.WHITE));
        }
    }
}