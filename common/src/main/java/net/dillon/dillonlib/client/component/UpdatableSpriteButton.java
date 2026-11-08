package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Extends a normal {@link SpriteIconButton}, but with the update sprite automatically rendering if necessary.
 * @since 1.2
 */
@Dill(env = DillEnv.CLIENT)
public class UpdatableSpriteButton extends SpriteIconButton.CenteredIcon {
    private final String orderName;
    private final BooleanSupplier shouldRenderUpdateSprite;

    public UpdatableSpriteButton(
            String orderName,
            int width,
            int height,
            int spriteWidth,
            int spriteHeight,
            BooleanSupplier shouldRenderUpdateSprite,
            WidgetSprites sprite,
            Component tooltip,
            OnPress onPress
    ) {
        super(width, height, DillonsTexts.BLANK, spriteWidth, spriteHeight, 0, 0, sprite, onPress, null, null, false);
        this.orderName = orderName;
        if (!tooltip.equals(Component.empty())) {
            this.setTooltip(Tooltip.create(tooltip));
        }
        this.shouldRenderUpdateSprite = shouldRenderUpdateSprite;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractContents(graphics, mouseX, mouseY, a);
        ClientTasks.renderUpdateIconOnButton(graphics, this);
    }

    /**
     * @return the button libName, used for correct alphabetical ordering.
     */
    public String getOrderName() {
        return this.orderName;
    }

    /**
     * @return if the update sprite should render on the button.
     */
    public BooleanSupplier shouldRenderUpdateSprite() {
        return this.shouldRenderUpdateSprite;
    }

    /**
     * @return a new {@link Builder}.
     */
    public static Builder builder(
            String orderName,
            Component modName,
            Identifier sprite,
            Button.OnPress onPress
    ) {
        return builder(orderName, modName, new WidgetSprites(sprite), onPress);
    }

    /**
     * @return a new {@link Builder}.
     */
    public static Builder builder(
            String orderName,
            Component modName,
            WidgetSprites sprites,
            Button.OnPress onPress
    ) {
        return new Builder(orderName, modName, sprites, onPress);
    }

    /**
     * A builder class for creating {@link UpdatableSpriteButton}s.
     */
    public static class Builder implements ComponentBuilder<UpdatableSpriteButton>, TooltipBuilder<Builder> {
        private final String orderName;
        private final Component modName;
        private final WidgetSprites sprites;
        private final Button.OnPress onPress;
        private Supplier<Component> tooltip = Component::empty;
        private BooleanSupplier hasUpdateSupplier = () -> false;
        private int width = 20;
        private int height = 20;
        private int spriteWidth = 16;
        private int spriteHeight = 16;

        private Builder(String orderName, Component modName, WidgetSprites sprites, Button.OnPress onPress) {
            this.orderName = orderName;
            this.modName = modName;
            this.sprites = sprites;
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

        public Builder updateSupplier(BooleanSupplier hasUpdateSupplier) {
            this.hasUpdateSupplier = hasUpdateSupplier;
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
        public UpdatableSpriteButton build() {
            Component actualTooltip = tooltip.get();
            if (hasUpdateSupplier.getAsBoolean()) {
                actualTooltip = Component.translatable("dillonlib.update_available", modName);
            }

            return new UpdatableSpriteButton(
                    orderName,
                    width,
                    height,
                    spriteWidth,
                    spriteHeight,
                    hasUpdateSupplier,
                    sprites,
                    actualTooltip,
                    onPress
            );
        }
    }
}