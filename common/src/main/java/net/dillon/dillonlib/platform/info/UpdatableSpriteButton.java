package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Extends a normal {@link SpriteIconButton}, but with the update sprite automatically rendering if necessary.
 */
public class UpdatableSpriteButton extends SpriteIconButton.CenteredIcon {
    private final String name;
    private final boolean shouldRenderUpdateSprite;

    private UpdatableSpriteButton(String name, WidgetSprites sprite, OnPress onPress, boolean shouldRenderUpdateSprite, int spriteWidth, int spriteHeight) {
        super(20, 20, Texts.BLANK, spriteWidth, spriteHeight, 0, 0, sprite, onPress, null, null, false);
        this.name = name;
        this.shouldRenderUpdateSprite = shouldRenderUpdateSprite;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractContents(graphics, mouseX, mouseY, a);
        ClientTasks.renderUpdateIconOnButton(graphics, this);
    }

    /**
     * Creates a default {@link UpdatableSpriteButton}.
     */
    public static UpdatableSpriteButton ofDefault(String name, Identifier sprite, Button.OnPress onPress, Component tooltip, boolean hasUpdate) {
        return of(name, sprite, onPress, tooltip, 16, 16, hasUpdate);
    }

    /**
     * Creates a {@link UpdatableSpriteButton} with a custom sprite width and height.
     */
    public static UpdatableSpriteButton of(String name, Identifier sprite, Button.OnPress onPress, Component tooltip, int spriteWidth, int spriteHeight, boolean hasUpdate) {
        UpdatableSpriteButton button = new UpdatableSpriteButton(name, new WidgetSprites(sprite), onPress, hasUpdate, spriteWidth, spriteHeight);
        if (!tooltip.equals(Component.empty())) {
            button.setTooltip(Tooltip.create(tooltip));
        }
        return button;
    }

    /**
     * @return the button libName, used for correct alphabetical ordering.
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