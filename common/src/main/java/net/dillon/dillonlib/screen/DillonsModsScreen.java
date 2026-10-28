
package net.dillon.dillonlib.screen;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.util.CommonWidgets;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays all of Dillon's platform menu buttons to configure each mod.
 */
public class DillonsModsScreen extends BasicDillonLibScreen {
    private final Screen parent;
    private final Int2ObjectMap<List<PlatformMenuButton>> buttons = new Int2ObjectOpenHashMap<>();
    private final HeaderAndFooterLayout headerAndFooter = new HeaderAndFooterLayout(this);
    private Button next, previous;
    private int currentPage = 1;

    public DillonsModsScreen(Screen parent) {
        super(Component.translatable("dillonlib.mods"));
        this.parent = parent;
    }

    @Override
    public void onClose() {
        // Set to parent screen on close
        this.minecraft.gui.setScreen(this.parent);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        // Dont try to navigate if cant
        if (!(buttons.size() > 4)) {
            return super.keyPressed(event);
        }

        if (event.key() == InputConstants.KEY_LEFT) {
            // Navigate to previous page
            previous();
            setFocused(previous);
            return true;
        } else if (event.key() == InputConstants.KEY_RIGHT) {
            // Navigate to next page
            next();
            setFocused(next);
            return true;
        }

        return super.keyPressed(event);
    }

    /**
     * Navigates to the next page.
     */
    private void next() {
        currentPage++;
        rebuildWidgets();
    }

    /**
     * Navigates to the previous page.
     */
    private void previous() {
        if (currentPage > 1) {
            currentPage--;
            rebuildWidgets();
        }
    }

    @Override
    public void widgets() {
        // Re-initialize buttons
        buttons.clear();

        // Order and sort all buttons
        List<PlatformMenuButton> orderedButtons = new ArrayList<>();
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {
            for (PlatformMenuButton p : clientModPlatform.menuButtons()) {
                if (p == null) {
                    continue;
                }

                UpdatableSpriteButton button = p.button();
                if (button != null) {
                    orderedButtons.add(p);
                }
            }
        });
        PlatformMenuButton.sortButtons(orderedButtons);

        // Update Int2ObjectMap accordingly
        int buttonPage = 1;
        int buttonCount = 0;
        for (PlatformMenuButton p : orderedButtons) {
            buttons.computeIfAbsent(buttonPage, key -> new ArrayList<>()).add(p);
            buttonCount++;

            if (buttonCount == 4) {
                buttonPage++;
                buttonCount = 0;
            }
        }

        // Clamp the current page to the available pages
        int maxPage = Math.max(1, buttons.size());
        currentPage = Math.clamp(currentPage, 1, maxPage);

        // Create grid
        GridLayout grid = new GridLayout();
        grid.columnSpacing(24);
        grid.rowSpacing(24);

        // Initialize the box variable
        GridLayout.RowHelper box = grid.createRowHelper(2);

        // Create header and footer
        LinearLayout footer = LinearLayout.horizontal().spacing(8);

        // Create previous button
        previous = Button.builder(Texts.PREVIOUS_PAGE, button -> previous())
                .width(20)
                .build();
        previous.active = currentPage > 1;

        // Create next button
        next = Button.builder(Texts.NEXT_PAGE, button -> next())
                .width(20)
                .build();
        next.active = buttons.size() > 1 && currentPage != buttons.size();

        // Add each button based on page number
        if (buttons.isEmpty()) {
            box.addChild(
                    FocusableTextWidget.builder(Component.translatable("dillonlib.mods_none"), getFont())
                            .build()
            );
        } else {
            buttons.getOrDefault(currentPage, List.of()).forEach(p -> {
                UpdatableSpriteButton button = p.button();
                p.consumer().accept(button);

                button.setSize(80, 80);
                ((ModifiableSpriteIconButton)button).setSpriteSize(64, 64);
                box.addChild(button);
            });

            if (buttons.size() > 4) {
                footer.addChild(previous);
                footer.addChild(next);
            }
        }

        // Align buttons
        CommonWidgets.centerGrid(this, grid);

        // Add title
        headerAndFooter.addTitleHeader(
                this.title.copy().withStyle(ChatFormatting.LIGHT_PURPLE),
                getFont()
        );

        // Arrange and register the header and footer widgets
        headerAndFooter.addToFooter(footer);
        headerAndFooter.arrangeElements();
        headerAndFooter.visitWidgets(this::addRenderableWidget);
    }
}