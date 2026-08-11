package net.dillon.dillonlib.platform.info;

import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.function.Consumer;

/**
 * Stores menu button data.
 * <p>The first boolean in the array represents the title screen boolean. The second boolean in the array represents the pause screen boolean.
 */
public record PlatformMenuButton(boolean titleCondition, boolean pauseCondition, SpriteIconButton menuButton, Consumer<SpriteIconButton> consumer) {
}