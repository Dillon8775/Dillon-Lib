package net.dillon.dillonlib.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.client.component.GridBuilder;
import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.client.component.UpdatableSpriteButton;
import net.dillon.dillonlib.client.impl.SpriteIconButtonImpl;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.dillon.dillonlib.widespread.DillonsWidgets;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays all of Dillon's platform menu buttons to configure each mod.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public class DillonsModsScreen extends DillonLibScreen {
    private final HeaderAndFooterLayout headerAndFooter = new HeaderAndFooterLayout(this);
    private final Int2ObjectMap<List<PlatformMenuButton>> buttons = new Int2ObjectOpenHashMap<>();
    private final List<PlatformMenuButton> orderedButtons = getOrderedButtons();
    private AbstractWidget next, previous;
    private int currentPage = 1;

    public DillonsModsScreen(Screen parent) {
        super(parent, Component.translatable("dillonlib.mods"));
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        // Dont try to navigate if cant
        if (!nextOrPrevious()) {
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

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClicked) {
        // Stop mouse clicking entirely if buttons aren't active
        if ((next.isHovered() && !next.isActive()) || (previous.isHovered() && !previous.isActive())) {
            return false;
        }

        return super.mouseClicked(event, doubleClicked);
    }

    /**
     * @return if the current size of the button list is greater than 4.
     */
    private boolean nextOrPrevious() {
        return orderedButtons.size() > 6;
    }

    /**
     * @return if the screen can navigate to the next page.
     */
    private boolean canNavigateNext() {
        return buttons.size() > 1 && currentPage != buttons.size();
    }

    /**
     * @return if the screen can navigate to the previous page.
     */
    private boolean canNavigatePrevious() {
        return currentPage > 1;
    }

    /**
     * Navigates to the next page.
     */
    private void next() {
        if (canNavigateNext()) {
            currentPage++;
            rebuildWidgets();
        }
    }

    /**
     * Navigates to the previous page.
     */
    private void previous() {
        if (canNavigatePrevious()) {
            currentPage--;
            rebuildWidgets();
        }
    }

    /**
     * @return the list of ordered buttons for each platform.
     */
    private List<PlatformMenuButton> getOrderedButtons() {
        List<PlatformMenuButton> orderedButtons = new ArrayList<>();

        // Add each platform menu button to the list
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {
            for (PlatformMenuButton p : clientModPlatform.menuButtons()) {
                if (p == null) {
                    continue;
                }

                UpdatableSpriteButton button = p.button();
                if (button != null && p.visible().getAsBoolean()) {
                    orderedButtons.add(p);
                }
            }
        });

        // Return the sorted list
        PlatformMenuButton.sortButtons(orderedButtons);
        return orderedButtons;
    }

    @Override
    public void widgets() {
        // Re-initialize buttons
        buttons.clear();

        // Calculate sizes
        int buttonPage = 1;
        int buttonCount = 0;
        int pageButtonCap = 4;
        if (orderedButtons.size() > 4) pageButtonCap = 6;

        // Update Int2ObjectMap accordingly
        for (PlatformMenuButton p : orderedButtons) {
            buttons.computeIfAbsent(buttonPage, key -> new ArrayList<>()).add(p);
            buttonCount++;

            if (buttonCount == pageButtonCap) {
                buttonPage++;
                buttonCount = 0;
            }
        }

        // Clamp the current page to the available pages
        int maxPage = Math.max(1, buttons.size());
        currentPage = Math.clamp(currentPage, 1, maxPage);

        // Get row size
        int rowSize = 2;
        if (buttons.getOrDefault(currentPage, List.of()).size() > 4) rowSize = 3;

        // Create grid
        GridBuilder builder = GridBuilder.create(this, rowSize);
        builder.getGrid().rowSpacing(24);
        builder.getGrid().columnSpacing(24);

        // Create footer buttons
        LinearLayout footer = LinearLayout.horizontal().spacing(8);

        // Create previous button
        previous = createWidget(
                Button.builder(DillonsTexts.PREVIOUS_PAGE, button -> previous())
                        .width(20)
                        .build(),
                this::canNavigatePrevious
        );

        // Create next button
        next = createWidget(
                Button.builder(DillonsTexts.NEXT_PAGE, button -> next())
                        .width(20)
                        .build(),
                this::canNavigateNext
        );

        // Add each button based on page number
        if (buttons.isEmpty()) {
            builder.getCurrentRow().addChild(
                    FocusableTextWidget.builder(Component.translatable("dillonlib.mods_none"), getFont())
                            .build()
            );
        } else {
            buttons.getOrDefault(currentPage, List.of()).forEach(p -> {
                UpdatableSpriteButton button = p.button();
                p.consumer().accept(button);

                int size = 80;
                if (buttons.getOrDefault(currentPage, List.of()).size() > 4) size = 64;
                int spriteSize = size - 16;

                button.setSize(size, size);
                ((SpriteIconButtonImpl)button).setSpriteSize(spriteSize, spriteSize);
                builder.getCurrentRow().addChild(button);
            });
        }

        if (orderedButtons.size() > 6) footer.addChild(previous);
        footer.addChild(DillonsWidgets.createCheckmarkButton(b -> onClose()));
        if (orderedButtons.size() > 6) footer.addChild(next);

        // Align buttons
        builder.buildAndCenter();

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