package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Extends a normal {@link SpriteIconButton}, but with the update sprite automatically rendering if necessary.
 * @since 1.2
 */
@Dill(env = DillEnv.CLIENT)
public class UpdatableSpriteButton extends SpriteIconButton {
    private final String orderName;
    private final BooleanSupplier shouldRenderUpdateSprite;

    public UpdatableSpriteButton(
            String orderName,
            int width,
            int height,
            BooleanSupplier shouldRenderUpdateSprite,
            ScaledWidgetSprites scaledWidgetSprites,
            Component tooltip,
            OnPress onPress
    ) {
        super(0, 0, width, height, DillonsTexts.BLANK, scaledWidgetSprites, DisplayState.ICON_ONLY, onPress, null, DEFAULT_NARRATION, false);
        this.orderName = orderName;
        if (!tooltip.equals(Component.empty())) {
            this.setTooltip(Tooltip.create(tooltip));
        }
        this.shouldRenderUpdateSprite = shouldRenderUpdateSprite;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics) {
        super.extractContents(graphics);
        ClientTasks.renderUpdateIconOnButton(graphics, this);
    }

    /**
     * @return the button order argumentName, used for correct alphabetical ordering.
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
            WidgetSprites widgetSprites,
            Button.OnPress onPress
    ) {
        return builder(orderName, modName, new ScaledWidgetSprites(widgetSprites, 16, 16), onPress);
    }

    /**
     * @return a new {@link Builder}.
     */
    public static Builder builder(
            String orderName,
            Component modName,
            ScaledWidgetSprites scaledWidgetSprites,
            Button.OnPress onPress
    ) {
        return new Builder(orderName, modName, scaledWidgetSprites, onPress);
    }

    /**
     * A builder class for creating {@link UpdatableSpriteButton}s.
     */
    public static class Builder implements ComponentBuilder<UpdatableSpriteButton>, TooltipBuilder<Builder> {
        private final String orderName;
        private final Component modName;
        private final ScaledWidgetSprites scaledWidgetSprites;
        private final Button.OnPress onPress;
        private Supplier<Component> tooltip = Component::empty;
        private BooleanSupplier hasUpdateSupplier = () -> false;
        private int width = 20;
        private int height = 20;

        private Builder(String orderName, Component modName, ScaledWidgetSprites scaledWidgetSprites, Button.OnPress onPress) {
            this.orderName = orderName;
            this.modName = modName;
            this.scaledWidgetSprites = scaledWidgetSprites;
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
                    hasUpdateSupplier,
                    scaledWidgetSprites,
                    actualTooltip,
                    onPress
            );
        }
    }
}