package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A common screen.
 * @since 1.2
 * @see ScreenBuilder
 * @see DillonLibMenuScreen
 */
@Dill(DillType.CLIENT)
public abstract class BasicDillonLibScreen extends Screen implements DillonLibScreen {
    private List<WidgetData> widgets;
    private final ScreenBuilder screenBuilder;

    public BasicDillonLibScreen(Component title) {
        super(title);
        this.screenBuilder = ScreenBuilder.of(this);
    }

    @Override
    public List<WidgetData> widgetData() {
        return this.widgets;
    }

    /**
     * @return the screen builder.
     */
    public ScreenBuilder builder() {
        return this.screenBuilder;
    }

    @Override
    protected void init() {
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

        screenBuilder.resetRenderBounds();
        drawGraphics(graphics);
    }

    /**
     * Draws graphics on the screen.
     * @see ScreenBuilder#textCenterAndGraphicsHeightDown(GuiGraphicsExtractor, Component)
     */
    protected void drawGraphics(GuiGraphicsExtractor graphics) {
    }
}