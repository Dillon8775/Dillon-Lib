package net.dillon.dillonlib.util;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraft.resources.Identifier;

/**
 * Common sprites used throughout Dillon's mods.
 */
@Dill(DillType.CLIENT)
public class CommonSprites {
    public static final Identifier UPDATE_AVAILABLE = Identifier.fromNamespaceAndPath(DillonLibMain.MOD_ID, "icon/update_available");
    public static final Identifier BOOK = Identifier.fromNamespaceAndPath(DillonLibMain.MOD_ID, "container/slot/book");
    public static final Identifier ENCHANTED_BOOK = Identifier.fromNamespaceAndPath(DillonLibMain.MOD_ID, "container/slot/enchanted_book");
}