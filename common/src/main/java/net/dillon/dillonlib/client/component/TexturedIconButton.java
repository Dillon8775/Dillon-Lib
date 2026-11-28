package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

/**
 * A button that represents a sprite icon button with a texture rather than a sprite.
 */
@Dill(env = DillEnv.CLIENT)
public class TexturedIconButton extends Button {
    private final Supplier<Identifier> texture;

    public TexturedIconButton(Identifier texture, Component tooltip, Button.OnPress onPress) {
        this(() -> texture, () -> tooltip, onPress);
    }

    public TexturedIconButton(Supplier<Identifier> texture, Supplier<Component> tooltip, Button.OnPress onPress) {
        super(0, 0, 20, 20, DillonsTexts.BLANK, onPress, DEFAULT_NARRATION);
        this.setTooltip(Tooltip.create(tooltip.get()));
        this.texture = texture;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics) {
        this.extractDefaultSprite(graphics);
        TextureBuilder.createSmallOnButton(
                graphics,
                this.texture.get(),
                this
        ).buildAndBlit(false);
    }
}