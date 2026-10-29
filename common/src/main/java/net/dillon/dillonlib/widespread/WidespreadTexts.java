package net.dillon.dillonlib.widespread;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

/**
 * Common texts that can be used throughout your mod.
 * @since 1.0
 */
public class WidespreadTexts {
    public static final Component BLANK = Component.literal("");
    public static final Component ON = Component.translatable("dillonlib.on");
    public static final Component OFF = Component.translatable("dillonlib.off");
    public static final Component YES = Component.translatable("dillonlib.yes");
    public static final Component NO = Component.translatable("dillonlib.no");
    public static final Component LETS_GO = Component.translatable("dillonlib.lets_go");
    public static final Component BACK = Component.translatable("dillonlib.back");
    public static final Component NEXT = Component.translatable("dillonlib.next");
    public static final Component NEXT_PAGE = Component.translatable("dillonlib.next_page");
    public static final Component PREVIOUS_PAGE = Component.translatable("dillonlib.previous_page");
    public static final Component OK = Component.translatable("dillonlib.ok");
    public static final Component OKAY = Component.translatable("dillonlib.okay");
    public static final Component PROCEED = Component.translatable("dillonlib.proceed");
    public static final Component CONFIRM = Component.translatable("dillonlib.confirm");
    public static final Component OPEN_FILE = Component.translatable("dillonlib.open_file");
    public static final Component OPEN_DIRECTORY = Component.translatable("dillonlib.open_directory");
    public static final Component SEARCH = Component.translatable("dillonlib.gui.search").withStyle(EditBox.SEARCH_HINT_STYLE);
}