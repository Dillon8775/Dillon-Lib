package net.dillon.dillonlib.client.impl;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.gui.components.SpriteIconButton;

/**
 * Allows modification of a {@link SpriteIconButton}s {@code spriteWidth} and {@code spriteHeight} value.
 */
@Dill(env = DillEnv.CLIENT)
public interface SpriteIconButtonImpl {
    void setSpriteSize(int spriteWidth, int spriteHeight);
}