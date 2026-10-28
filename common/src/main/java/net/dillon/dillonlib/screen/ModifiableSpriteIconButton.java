package net.dillon.dillonlib.screen;

/**
 * Allows modification of a {@link net.minecraft.client.gui.components.SpriteIconButton}s {@code spriteWidth} and {@code spriteHeight} value.
 */
public interface ModifiableSpriteIconButton {
    void setSpriteSize(int spriteWidth, int spriteHeight);
}