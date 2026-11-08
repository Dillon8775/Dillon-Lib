package net.dillon.dillonlib.client.impl;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.screens.Screen;

/**
 * Used to implement a fix for the debug options screen.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public interface DebugOptionsScreenImpl {
    void setParent(Screen parent);
}