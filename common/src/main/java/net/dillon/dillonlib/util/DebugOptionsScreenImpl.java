package net.dillon.dillonlib.util;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.screens.Screen;

/**
 * Used to implement a fix for the debug options screen.
 */
@Dill(DillType.CLIENT)
public interface DebugOptionsScreenImpl {
    void setParent(Screen parent);
    Screen getParent();
}