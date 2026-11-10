package net.dillon.dillonlib.widespread;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.impl.FocusableTextWidgetImpl;
import net.dillon.dillonlib.client.impl.WidgetsImpl;
import net.dillon.dillonlib.client.screen.DillonLibScreen;
import net.dillon.dillonlib.mixin.accessor.ScreenInvoker;
import net.dillon.dillonlib.platform.info.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.getFont;

/**
 * Utility class for creating common widgets.
 *
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class DillonsWidgets {

    /**
     * Creates an option widget from an {@link OptionInstance}.
     */
    public static AbstractWidget createOption(OptionInstance<?> instance) {
        return createOption(instance, 150);
    }

    /**
     * Creates an option widget from an {@link OptionInstance} with a custom width.
     */
    public static AbstractWidget createOption(OptionInstance<?> instance, int width) {
        return instance.createButton(Minecraft.getInstance().options, 0, 0, width);
    }

    /**
     * @return a checkmark button.
     */
    public static SpriteIconButton createCheckmarkButton(Button.OnPress onPress) {
        return SpriteIconButton.builder(DillonsTexts.BLANK, onPress, true)
                .sprite(DillonsSprites.CHECKMARK, 9, 8)
                .width(20)
                .build();
    }

    /**
     * @return a {@link MultiLineTextWidget} and centers automatically.
     */
    public static MultiLineTextWidget createMultiLineText(Component text) {
        return createMultiLineText(text, true);
    }

    /**
     * @return a {@link MultiLineTextWidget} with the option to be centered.
     */
    public static MultiLineTextWidget createMultiLineText(Component text, boolean centered) {
        return new MultiLineTextWidget(text, getFont()).setCentered(centered);
    }

    /**
     * Creates a {@link FocusableTextWidget} with version text for a mod.
     */
    public static AbstractWidget createVersionLabel(ModInfo info, WidgetsImpl widgets) {
        FocusableTextWidget focusableTextWidget = FocusableTextWidget.builder(
                Component.translatable("dillonlib.version", info.version()),
                getFont()
        ).build();
        ((FocusableTextWidgetImpl) focusableTextWidget).setHasUpdate(info.hasUpdate());
        ((FocusableTextWidgetImpl) focusableTextWidget).setUpdateLink(info.link());

        return widgets.createWidget(
                focusableTextWidget,
                isActive -> {
                    if (info.hasUpdate()) {
                        return Component.translatable("dillonlib.update_available_message", info.name());
                    } else {
                        return Component.translatable("dillonlib.up_to_date");
                    }
                }
        );
    }

    /**
     * Arranges and adds all elements to a header/footer.
     */
    public static void finalizeHeaderAndFooter(Screen screen, HeaderAndFooterLayout headerAndFooterLayout) {
        headerAndFooterLayout.arrangeElements();
        headerAndFooterLayout.visitWidgets(widget -> ((ScreenInvoker) screen).addRenderableModWidget(widget));
    }

    /**
     * Adds built-in title text to a {@link DillonLibScreen}.
     */
    public static void addTitleToHeader(DillonLibScreen screen) {
        addTitleToHeader(screen.headerAndFooterLayout, screen.getTitle());
    }

    /**
     * Adds title text to the screen.
     */
    public static void addTitleToHeader(HeaderAndFooterLayout headerAndFooterLayout, Component title) {
        headerAndFooterLayout.addTitleHeader(title, getFont());
    }

    /**
     * Adds title text at a custom y position.
     */
    public static void addTitleText(Screen screen, Component title, int y) {
        int textWidth = getTextWidth(title);
        addTitleText(
                screen,
                title,
                textWidth,
                centeredTitlePos(screen, textWidth),
                y
        );
    }

    /**
     * Adds title text to the screen at a custom position.
     */
    public static void addTitleText(Screen screen, Component title, int textWidth, int x, int y) {
        ((ScreenInvoker) screen).addRenderableModWidget(
                new StringWidget(x, y, textWidth, 9, title, getFont())
        );
    }

    /**
     * @return the length (width) for text.
     */
    public static int getTextWidth(Component text) {
        return getFont().width(text);
    }

    /**
     * @return the center position for text.
     */
    public static int centeredTitlePos(Screen screen, int textWidth) {
        return screen.width / 2 - textWidth / 2;
    }
}