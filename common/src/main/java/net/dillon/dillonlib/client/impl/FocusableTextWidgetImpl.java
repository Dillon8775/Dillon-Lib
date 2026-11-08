package net.dillon.dillonlib.client.impl;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsWidgets;
import net.minecraft.client.gui.components.FocusableTextWidget;

/**
 * Provides support for {@link FocusableTextWidget} to be clicked and open a link.
 * @since 1.3
 * @see DillonsWidgets
 */
@Dill(env = DillEnv.CLIENT)
public interface FocusableTextWidgetImpl {
    void setHasUpdate(boolean hasUpdate);
    void setUpdateLink(String updateLink);
}