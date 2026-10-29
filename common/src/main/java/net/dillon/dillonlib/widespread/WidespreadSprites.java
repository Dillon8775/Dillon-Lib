package net.dillon.dillonlib.widespread;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.resources.Identifier;

import static net.dillon.dillonlib.core.DillonLibMain.dillonLibIdentifier;

/**
 * Common sprites used throughout Dillon's mods.
 */
@Dill(DillType.CLIENT)
public class WidespreadSprites {
    public static final Identifier MENU_BUTTON = dillonLibIdentifier("icon/menu_button");
    public static final Identifier UPDATE_AVAILABLE = dillonLibIdentifier("icon/update_available");
    public static final Identifier CHECKMARK = Identifier.withDefaultNamespace("icon/checkmark");
}