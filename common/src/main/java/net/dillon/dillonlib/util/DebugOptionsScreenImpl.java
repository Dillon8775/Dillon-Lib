package net.dillon.dillonlib.util;

import net.minecraft.client.gui.screens.Screen;

/**
 * Used to implement a fix for the debug options screen.
 */
public interface DebugOptionsScreenImpl {
    void setParent(Screen parent);
    Screen getParent();
}