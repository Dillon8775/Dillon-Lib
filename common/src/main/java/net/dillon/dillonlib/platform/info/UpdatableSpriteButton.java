package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.widespread.WidespreadTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;

/**
 * Extends a normal {@link SpriteIconButton}, but with the update sprite automatically rendering if necessary.
 */
public class UpdatableSpriteButton extends SpriteIconButton.CenteredIcon {
    private final String orderName;
    private final BooleanSupplier shouldRenderUpdateSprite;

    public UpdatableSpriteButton(String orderName, int width, int height, int spriteWidth, int spriteHeight, BooleanSupplier shouldRenderUpdateSprite, Component tooltip, WidgetSprites sprite, OnPress onPress) {
        super(width, height, WidespreadTexts.BLANK, spriteWidth, spriteHeight, 0, 0, sprite, onPress, null, null, false);
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
}