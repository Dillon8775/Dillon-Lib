package net.dillon.dillonlib.client;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A better version of {@link OptionsList}, with more user-friendly appearances, centering and positioning.
 * @since 1.0
 * @see ModernWidgetOptions
 */
@Dill(DillType.CLIENT)
public class ModernListWidget extends ContainerObjectSelectionList<ModernListWidget.ModWidgetEntry> {
    private static final int ROW_WIDTH = 310;
    private static final int ROW_SPACING = 160;

    /**
     * Create a new {@link ModernListWidget}.
     */
    public ModernListWidget(Minecraft client, int width, OptionsSubScreen optionsScreen) {
        super(client, width, optionsScreen.layout.getContentHeight(), optionsScreen.layout.getHeaderHeight(), 25);
        this.centerListVertically = false;
    }

    /**
     * Adds a row of buttons to the list.
     */
    public void addRow(AbstractWidget firstButton, @Nullable AbstractWidget secondButton) {
        List<AbstractWidget> buttons = new ArrayList<>();
        buttons.add(firstButton);
        if (secondButton != null) {
            buttons.add(secondButton);
        }
        this.addEntry(ModWidgetEntry.create(buttons));
        this.layoutButtons(false);
    }

    /**
     * Adds a single button to the list of buttons. This button will take up a whole row space.
     */
    public void addSingleOptionEntry(AbstractWidget button) {
        button.setWidth(ROW_WIDTH);
        List<AbstractWidget> buttons = new ArrayList<>();
        buttons.add(button);
        this.addEntry(ModWidgetEntry.create(buttons));
        this.layoutButtons(false);
    }

    /**
     * Adds a {@link List} of buttons to the screen.
     */
    public void addAll(List<AbstractWidget> buttons) {
        for (int i = 0; i < buttons.size(); i += 2) {
            this.addRow(buttons.get(i), i < buttons.size() - 1 ? buttons.get(i + 1) : null);
        }
    }

    /**
     * Repositions row widgets after the list itself has been moved or resized.
     */
    public void layoutButtons(boolean centerSingleButtons) {
        int rowLeft = this.getRowLeft();

        for (ModWidgetEntry entry : this.children()) {
            List<AbstractWidget> widgets = entry.widgets;
            if (widgets.size() == 1) {
                AbstractWidget widget = widgets.getFirst();
                widget.setX(centerSingleButtons && widget.getWidth() < ROW_WIDTH ? rowLeft + (ROW_WIDTH - widget.getWidth()) / 2 : rowLeft);
            } else if (widgets.size() >= 2) {
                widgets.get(0).setX(rowLeft);
                widgets.get(1).setX(rowLeft + ROW_SPACING);
            }
        }
    }

    /**
     * @return the total height required to display all current entries.
     */
    public int getEntryContentHeight() {
        int height = 0;

        for (ModWidgetEntry entry : this.children()) {
            height += entry.getHeight() + 1;
        }

        return height;
    }

    /**
     * @return the total row width for the list widget.
     */
    @Override
    public int getRowWidth() {
        return ROW_WIDTH;
    }

    public static class ModWidgetEntry extends Entry<ModWidgetEntry> {
        public final List<AbstractWidget> widgets;

        private ModWidgetEntry(List<AbstractWidget> widgets) {
            this.widgets = widgets;
        }

        public static ModWidgetEntry create(List<AbstractWidget> widgets) {
            return new ModWidgetEntry(widgets);
        }

        @Override
        public void extractContent(@NonNull GuiGraphicsExtractor context, int mouseX, int mouseY, boolean hovered, float deltaTicks) {
            for (AbstractWidget widget : this.widgets) {
                widget.setY(this.getY());
                widget.extractRenderState(context, mouseX, mouseY, deltaTicks);
            }
        }

        @Override
        public @NonNull List<? extends GuiEventListener> children() {
            return this.widgets;
        }

        @Override
        public @NonNull List<? extends NarratableEntry> narratables() {
            return this.widgets;
        }
    }
}