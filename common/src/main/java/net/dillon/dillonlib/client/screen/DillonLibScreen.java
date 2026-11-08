package net.dillon.dillonlib.client.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.impl.WidgetData;
import net.dillon.dillonlib.client.impl.WidgetsImpl;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

/**
 * A common screen.
 * @since 1.2
 * @see DillonLibMenuScreen
 */
@Dill(env = DillEnv.CLIENT)
public abstract class DillonLibScreen extends Screen implements WidgetsImpl {
    private final Screen lastScreen;
    private List<WidgetData> widgets;
    public HeaderAndFooterLayout headerAndFooterLayout = new HeaderAndFooterLayout(this);

    public DillonLibScreen(Component title) {
        this(null, title);
    }

    public DillonLibScreen(Screen lastScreen, Component title) {
        super(title);
        this.lastScreen = lastScreen;
    }

    @Override
    public void onClose() {
        openScreen(lastScreen);
    }

    // Use widgets method instead
    @Override
    protected final void init() {
        this.widgets = new ArrayList<>();

        super.init();
        this.widgets();

        for (WidgetData data : widgetData()) {
            setWidgetPositions(data);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        drawWidgetData(graphics, mouseX, mouseY);
        drawGraphics(graphics);
    }

    /**
     * Draws graphics on the screen.
     */
    protected void drawGraphics(GuiGraphicsExtractor graphics) {
    }

    /**
     * Initializes widget data.
     */
    @Override
    public List<WidgetData> widgetData() {
        return this.widgets;
    }
}