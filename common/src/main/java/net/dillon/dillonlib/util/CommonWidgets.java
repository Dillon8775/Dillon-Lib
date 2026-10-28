package net.dillon.dillonlib.util;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.mixin.accessor.ScreenInvoker;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.BooleanSupplier;

/**
 * Utility class for creating common widgets.
 */
@Dill(DillType.CLIENT)
public class CommonWidgets {

    /**
     * @return a done button.
     */
    public static AbstractWidget createDoneButton(Screen screen) {
        return createDoneButton(screen, 200);
    }

    /**
     * @return a done button with a custom width.
     */
    public static AbstractWidget createDoneButton(Screen screen, int width) {
        return Button.builder(CommonComponents.GUI_DONE, button -> screen.onClose()).width(width).build();
    }

    /**
     * Creates a {@code small} {@link UpdatableSpriteButton}.
     */
    public static UpdatableSpriteButton createSmallUpdatableSpriteButton(String orderName, Component modName, BooleanSupplier updateSupplier, Component defaultTooltip, Identifier sprite, Button.OnPress onPress) {
        return createUpdatableSpriteButton(
                orderName,
                modName,
                20,
                20,
                16,
                16,
                updateSupplier,
                defaultTooltip,
                sprite,
                onPress
        );
    }

    /**
     * Creates a {@link UpdatableSpriteButton} with custom size options.
     */
    public static UpdatableSpriteButton createUpdatableSpriteButton(String orderName, Component modName, int width, int height, int spriteWidth, int spriteHeight, BooleanSupplier updateSupplier, Component defaultTooltip, Identifier sprite, Button.OnPress onPress) {
        Component tooltip = defaultTooltip;
        if (updateSupplier.getAsBoolean()) {
            tooltip = Component.translatable("dillonlib.update_available", modName);
        }

        return new UpdatableSpriteButton(
                orderName,
                width,
                height,
                spriteWidth,
                spriteHeight,
                updateSupplier,
                tooltip,
                new WidgetSprites(sprite),
                onPress
        );
    }

    /**
     * Centers a grid in place on a screen and adds all widgets to the screen.
     */
    public static void centerGrid(Screen screen, GridLayout grid) {
        centerGrid(screen, grid, true);
    }

    /**
     * Centers a grid in place on a screen with the option to add widgets to the screen.
     */
    public static void centerGrid(Screen screen, GridLayout grid, boolean addWidgets) {
        grid.arrangeElements();
        FrameLayout.centerInRectangle(grid, screen.getRectangle());

        if (addWidgets) {
            grid.visitWidgets(widget -> ((ScreenInvoker)screen).addRenderableModWidget(widget));
        }
    }
}