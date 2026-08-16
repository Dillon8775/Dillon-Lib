package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.WidgetSprites;

/**
 * Extends a normal {@link SpriteIconButton}, but with the update sprite automatically rendering if necessary.
 */
public class UpdatableSpriteButton extends SpriteIconButton.CenteredIcon {
    private final String name;
    private final boolean shouldRenderUpdateSprite;

    public UpdatableSpriteButton(String name, WidgetSprites sprite, OnPress onPress, boolean shouldRenderUpdateSprite) {
        super(20, 20, Texts.BLANK, 16, 16, 0, 0, sprite, onPress, null, null, false);
        this.name = name;
        this.shouldRenderUpdateSprite = shouldRenderUpdateSprite;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractContents(graphics, mouseX, mouseY, a);
        ClientTasks.renderUpdateIconOnButton(graphics, this);
    }

    /**
     * @return the button name, used for correct alphabetical ordering.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return if the update sprite should render on the button.
     */
    public boolean shouldRenderUpdateSprite() {
        return this.shouldRenderUpdateSprite;
    }
}