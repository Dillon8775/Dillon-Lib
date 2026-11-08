package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A basic representation for a sprite buttons, with ways to create custom sounds for each button.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class SpriteIconButtonWithSound extends SpriteIconButton.CenteredIcon {
    private final Consumer<SoundManager> soundManager;

    public SpriteIconButtonWithSound(
            int width,
            int height,
            int spriteWidth,
            int spriteHeight,
            Consumer<SoundManager> soundManager,
            WidgetSprites sprites,
            Supplier<Component> tooltip,
            OnPress onPress
    ) {
        super(width, height, DillonsTexts.BLANK, spriteWidth, spriteHeight, 0, 0, sprites, onPress, tooltip.get(), null, false);
        this.soundManager = soundManager;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        if (this.soundManager == null) {
            super.playDownSound(soundManager);
        } else {
            this.soundManager.accept(soundManager);
        }
    }

    /**
     * @return a new {@link Builder}.
     */
    public static Builder builder(
            Identifier sprite,
            Consumer<SoundManager> soundManager,
            Button.OnPress onPress
    ) {
        return builder(new WidgetSprites(sprite), soundManager, onPress);
    }

    /**
     * @return a new {@link Builder}.
     */
    public static Builder builder(
            WidgetSprites sprites,
            Consumer<SoundManager> soundManager,
            Button.OnPress onPress
    ) {
        return new Builder(sprites, soundManager, onPress);
    }

    /**
     * A builder class for {@link SpriteIconButtonWithSound}.
     */
    public static class Builder implements ComponentBuilder<SpriteIconButtonWithSound>, TooltipBuilder<Builder> {
        private final WidgetSprites sprites;
        private final Consumer<SoundManager> soundManager;
        private final Button.OnPress onPress;
        private Supplier<Component> tooltip = Component::empty;
        private int width = 20;
        private int height = 20;
        private int spriteWidth = 16;
        private int spriteHeight = 16;

        private Builder(WidgetSprites sprites, Consumer<SoundManager> soundManager, Button.OnPress onPress) {
            this.sprites = sprites;
            this.soundManager = soundManager;
            this.onPress = onPress;
        }

        @Override
        public Builder tooltip(Component tooltip) {
            return tooltip(() -> tooltip);
        }

        @Override
        public Builder tooltip(Supplier<Component> tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder spriteWidth(int spriteWidth) {
            this.spriteWidth = spriteWidth;
            return this;
        }

        public Builder spriteHeight(int spriteHeight) {
            this.spriteHeight = spriteHeight;
            return this;
        }

        @Override
        public SpriteIconButtonWithSound build() {
            return new SpriteIconButtonWithSound(
                    width,
                    height,
                    spriteWidth,
                    spriteHeight,
                    soundManager,
                    sprites,
                    tooltip,
                    onPress
            );
        }
    }
}