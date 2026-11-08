package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

/**
 * A builder class for creating basic {@link SpriteIconButton}s.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class BasicSpriteIconButtonBuilder implements ComponentBuilder<SpriteIconButton>, TooltipBuilder<BasicSpriteIconButtonBuilder> {
    private final WidgetSprites sprites;
    private final Button.OnPress onPress;
    private Supplier<Component> tooltip = Component::empty;
    private int spriteWidth = 16;
    private int spriteHeight = 16;

    private BasicSpriteIconButtonBuilder(WidgetSprites sprites, Button.OnPress onPress) {
        this.sprites = sprites;
        this.onPress = onPress;
    }

    public static BasicSpriteIconButtonBuilder builder(Identifier sprite, Button.OnPress onPress) {
        return builder(new WidgetSprites(sprite), onPress);
    }

    public static BasicSpriteIconButtonBuilder builder(WidgetSprites sprites, Button.OnPress onPress) {
        return new BasicSpriteIconButtonBuilder(sprites, onPress);
    }

    @Override
    public BasicSpriteIconButtonBuilder tooltip(Component tooltip) {
        return tooltip(() -> tooltip);
    }

    @Override
    public BasicSpriteIconButtonBuilder tooltip(Supplier<Component> tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public BasicSpriteIconButtonBuilder spriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
        return this;
    }

    public BasicSpriteIconButtonBuilder spriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
        return this;
    }

    public SpriteIconButton build() {
        return SpriteIconButton.builder(DillonsTexts.BLANK, onPress, true)
                .sprite(sprites, spriteWidth, spriteHeight)
                .width(20)
                .tooltip(tooltip.get())
                .build();
    }
}