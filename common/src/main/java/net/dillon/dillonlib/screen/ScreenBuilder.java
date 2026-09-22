package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.dillonlib.task.ClientTasks.getFont;

/**
 * Makes building screens easier.
 * @since 1.2
 * @see BasicDillonLibScreen
 * @see DillonLibMenuScreen
 * @see WidgetData
 */
@Dill(DillType.CLIENT)
public class ScreenBuilder {
    private final Screen screen;
    private int initialWidth = 0; // The first width initialized
    private int initialHeight = 0; // The first height initialized
    private int currentWidth = 0; // The current width of the screen builder
    private int currentHeight = 0; // The current height of the screen builder
    private int renderWidth = 0; // The current width of the screen builder, which is updated and then reset every frame. Really should only be used for text or constant renderable things
    private int renderHeight = 0; // The current height of the screen builder, which is updated and then reset every frame. Really should only be used for text or constant renderable things

    private ScreenBuilder(Screen screen) {
        this.screen = screen;
    }

    /**
     * Creates a new {@code ScreenBuilder}.
     * @param screen the screen to build on.
     * @return a new screen builder.
     */
    public static ScreenBuilder of(Screen screen) {
        return new ScreenBuilder(screen);
    }

    /**
     * Creates a new {@code ScreenBuilder} with a specific position.
     * @param screen the screen to build on.
     * @param pos the position.
     * @return a screen builder.
     */
    private static ScreenBuilder ofPos(Screen screen, ScreenPos pos) {
        ScreenBuilder builder = of(screen);

        builder.width(pos, Type.WIDTH).apply();
        builder.height(pos, Type.HEIGHT).apply();

        builder.initialWidth = builder.getCurrentWidth();
        builder.initialHeight = builder.getCurrentHeight();

        return builder;
    }

    /**
     * @return a bottom-centered screen builder.
     */
    public static ScreenBuilder ofBottomCentered(Screen screen) {
        return ofPos(screen, ScreenPos.BOTTOM_CENTER);
    }

    /**
     * @return a centered screen builder.
     */
    public static ScreenBuilder ofCentered(Screen screen) {
        return ofPos(screen, ScreenPos.CENTER);
    }

    /**
     * @return a top-centered screen builder.
     */
    public static ScreenBuilder ofTopCentered(Screen screen) {
        return ofPos(screen, ScreenPos.TOP_CENTER);
    }

    /**
     * @return a bottom-left screen builder.
     */
    public static ScreenBuilder ofBottomLeft(Screen screen) {
        return ofPos(screen, ScreenPos.BOTTOM_LEFT);
    }

    /**
     * @return a bottom-right screen builder.
     */
    public static ScreenBuilder ofBottomRight(Screen screen) {
        return ofPos(screen, ScreenPos.BOTTOM_RIGHT);
    }

    /**
     * @return a top-left screen builder.
     */
    public static ScreenBuilder ofTopLeft(Screen screen) {
        return ofPos(screen, ScreenPos.TOP_LEFT);
    }

    /**
     * @return a top-right screen builder.
     */
    public static ScreenBuilder ofTopRight(Screen screen) {
        return ofPos(screen, ScreenPos.TOP_RIGHT);
    }

    /**
     * @return the current width for this builder.
     */
    private int getCurrentWidth() {
        return currentWidth;
    }

    /**
     * @return the current height for this builder.
     */
    private int getCurrentHeight() {
        return currentHeight;
    }

    /**
     * Captures the width in its current place.
     */
    public int captureWidth() {
        return new ScreenValue(currentWidth, Type.WIDTH).pop();
    }

    /**
     * Captures the height in its current place.
     */
    public int captureHeight() {
        return new ScreenValue(currentHeight, Type.HEIGHT).pop();
    }

    /**
     * Captures the render width in its current place.
     */
    public int captureRenderWidth() {
        return new ScreenValue(renderWidth, Type.RENDER_WIDTH).pop();
    }

    /**
     * Captures the render height in its current place.
     */
    public int captureRenderHeight() {
        return new ScreenValue(renderHeight, Type.RENDER_HEIGHT).pop();
    }

    /**
     * @return the default offset for modifying width and height.
     */
    public int defaultOffset() {
        return 24;
    }

    /**
     * @return the default render offset for modifying render width and render height.
     */
    public int defaultRenderOffset() {
        return 20;
    }

    /**
     * Resets the width and height to the initial values.
     */
    public void resetBounds() {
        currentWidth = initialWidth;
        currentHeight = initialHeight;
    }

    /**
     * Resets the render bounds to the initial values.
     */
    public void resetRenderBounds() {
        renderWidth = initialWidth;
        renderHeight = initialHeight;
    }

    /**
     * Draws text and then moves the height down.
     */
    public ScreenValue textCenterAndHeightDown(GuiGraphicsExtractor graphics, Component text) {
        return textCenterAndHeightDown(graphics, text, defaultRenderOffset());
    }

    /**
     * Draws text and then moves the height down by a specific amount.
     */
    public ScreenValue textCenterAndHeightDown(GuiGraphicsExtractor graphics, Component text, int amount) {
        textCenter(graphics, text);
        return renderHeightDown(amount);
    }

    /**
     * Draws text in a centered fashion.
     */
    public ScreenValue textCenter(GuiGraphicsExtractor graphics, Component text) {
        int position = (width(ScreenPos.CENTER, Type.WIDTH).pop()) - getFont().width(text) / 2;
        graphics.text(screen.getFont(), text, position, renderHeight, CommonColors.WHITE);
        return new ScreenValue(position, Type.RENDER_HEIGHT);
    }

    /**
     * @return the width at a specific screen position.
     * @param pos the screen position.
     */
    public ScreenValue width(ScreenPos pos, Type type) {
        int value = switch (pos) {
            case BOTTOM_LEFT, TOP_LEFT -> 0;
            case BOTTOM_RIGHT, TOP_RIGHT -> screen.width - 20;
            case BOTTOM_CENTER, CENTER, TOP_CENTER -> screen.width / 2;
        };

        return new ScreenValue(value, type);
    }

    /**
     * @param widget the widget to reset width to.
     * @return the width at the widget's position.
     */
    public ScreenValue width(AbstractWidget widget) {
        return new ScreenValue(widget.getX(), Type.WIDTH);
    }

    /**
     * Calculates the width at the center, and returns the new value.
     */
    public ScreenValue widthCenter() {
        return width(ScreenPos.CENTER, Type.WIDTH);
    }

    /**
     * Calculates the width at the bottom left, and returns the new value.
     */
    public ScreenValue widthBottomSideLeft() {
        return width(ScreenPos.BOTTOM_LEFT, Type.WIDTH);
    }

    /**
     * Calculates the width at the bottom right, and returns the new value.
     */
    public ScreenValue widthBottomSideRight() {
        return width(ScreenPos.BOTTOM_RIGHT, Type.WIDTH);
    }

    /**
     * Calculates the current width moved right by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue widthRight() {
        return widthRight(defaultOffset());
    }

    /**
     * Calculates the current width moved right, and returns the new value.
     */
    public ScreenValue widthRight(int amount) {
        return valueAdd(currentWidth, amount, Type.WIDTH);
    }

    /**
     * Calculates the current width moved left by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue widthLeft() {
        return widthLeft(defaultOffset());
    }

    /**
     * Calculates the current width moved left, and returns the new value.
     */
    public ScreenValue widthLeft(int amount) {
        return valueNegate(currentWidth, amount, Type.WIDTH);
    }

    /**
     * @param widget the widget to reset render width to.
     * @return the render width at the widget's position.
     */
    public ScreenValue renderWidth(AbstractWidget widget) {
        return new ScreenValue(widget.getX(), Type.RENDER_WIDTH);
    }

    /**
     * Calculates the render width at the center, and returns the new value.
     */
    public ScreenValue renderWidthCenter() {
        return width(ScreenPos.CENTER, Type.RENDER_WIDTH);
    }

    /**
     * Calculates the render width at the bottom left, and returns the new value.
     */
    public ScreenValue renderWidthSideLeft() {
        return width(ScreenPos.BOTTOM_LEFT, Type.RENDER_WIDTH);
    }

    /**
     * Calculates the render width at the bottom right, and returns the new value.
     */
    public ScreenValue renderWidthSideRight() {
        return width(ScreenPos.BOTTOM_RIGHT, Type.RENDER_WIDTH);
    }

    /**
     * Sets the render width.
     */
    public ScreenValue renderWidth(int amount) {
        return valueAdd(amount, 0, Type.RENDER_WIDTH);
    }

    /**
     * Calculates the current render width moved right by {@link ScreenBuilder#defaultRenderOffset()}, and returns the new value.
     */
    public ScreenValue renderWidthRight() {
        return renderWidthRight(defaultRenderOffset());
    }

    /**
     * Calculates the current render width moved right, and returns the new value.
     */
    public ScreenValue renderWidthRight(int amount) {
        return valueAdd(renderWidth, amount, Type.RENDER_WIDTH);
    }

    /**
     * Calculates the current render width moved left by {@link ScreenBuilder#defaultRenderOffset()}, and returns the new value.
     */
    public ScreenValue renderWidthLeft() {
        return renderWidthLeft(defaultRenderOffset());
    }

    /**
     * Calculates the current render width moved left, and returns the new value.
     */
    public ScreenValue renderWidthLeft(int amount) {
        return valueNegate(renderWidth, amount, Type.RENDER_WIDTH);
    }

    /**
     * Calculates the height at a specific screen position, and returns the new value.
     */
    public ScreenValue height(ScreenPos pos, Type type) {
        int value = switch (pos) {
            case TOP_CENTER, TOP_LEFT, TOP_RIGHT -> 0;
            case BOTTOM_CENTER, BOTTOM_LEFT, BOTTOM_RIGHT -> screen.height - 20;
            case CENTER -> screen.height / 2;
        };

        return new ScreenValue(value, type);
    }

    /**
     * @param widget the widget to reset height to.
     * @return the height at the widget's position.
     */
    public ScreenValue height(AbstractWidget widget) {
        return new ScreenValue(widget.getY(), Type.HEIGHT);
    }

    /**
     * Calculates the height at the bottom center, and returns the new value.
     */
    public ScreenValue heightBottom() {
        return height(ScreenPos.BOTTOM_CENTER, Type.HEIGHT);
    }

    /**
     * Calculates the height at the center, and returns the new value.
     */
    public ScreenValue heightCenter() {
        return height(ScreenPos.CENTER, Type.HEIGHT);
    }

    /**
     * Calculates the height at the top center, and returns the new value.
     */
    public ScreenValue heightTop() {
        return height(ScreenPos.TOP_CENTER, Type.HEIGHT);
    }

    /**
     * Calculates the current height moved down by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue heightDown() {
        return heightDown(defaultOffset());
    }

    /**
     * Calculates the current height moved down, and returns the new value.
     */
    public ScreenValue heightDown(int amount) {
        return valueAdd(currentHeight, amount, Type.HEIGHT);
    }

    /**
     * Calculates the current height moved up by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue heightUp() {
        return heightUp(defaultOffset());
    }

    /**
     * Calculates the current height moved up, and returns the new value.
     */
    public ScreenValue heightUp(int amount) {
        return valueNegate(currentHeight, amount, Type.HEIGHT);
    }

    /**
     * @param widget the widget to reset render height to.
     * @return the render height at the widget's position.
     */
    public ScreenValue renderHeight(AbstractWidget widget) {
        return new ScreenValue(widget.getY(), Type.RENDER_HEIGHT);
    }

    /**
     * Calculates the render height at the bottom center, and returns the new value.
     */
    public ScreenValue renderHeightBottom() {
        return height(ScreenPos.BOTTOM_CENTER, Type.RENDER_HEIGHT);
    }

    /**
     * Calculates the render height at the center, and returns the new value.
     */
    public ScreenValue renderHeightCenter() {
        return height(ScreenPos.CENTER, Type.RENDER_HEIGHT);
    }

    /**
     * Calculates the render height at the top center, and returns the new value.
     */
    public ScreenValue renderHeightTop() {
        return height(ScreenPos.TOP_CENTER, Type.RENDER_HEIGHT);
    }

    /**
     * Sets the render height.
     */
    public ScreenValue renderHeight(int amount) {
        return valueAdd(amount, 0, Type.RENDER_HEIGHT);
    }

    /**
     * Calculates the current render height moved down by {@link ScreenBuilder#defaultRenderOffset()}, and returns the new value.
     */
    public ScreenValue renderHeightDown() {
        return renderHeightDown(defaultRenderOffset());
    }

    /**
     * Calculates the current render height moved down, and returns the new value.
     */
    public ScreenValue renderHeightDown(int amount) {
        return valueAdd(renderHeight, amount, Type.RENDER_HEIGHT);
    }

    /**
     * Calculates the current render height moved up by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue renderHeightUp() {
        return renderHeightUp(defaultRenderOffset());
    }

    /**
     * Calculates the current render height moved up, and returns the new value.
     */
    public ScreenValue renderHeightUp(int amount) {
        return valueNegate(renderHeight, amount, Type.RENDER_HEIGHT);
    }

    /**
     * Adds a value.
     */
    private ScreenValue valueAdd(int value, int amount, Type type) {
        return new ScreenValue(value + amount, type);
    }

    /**
     * Negates a value.
     */
    private ScreenValue valueNegate(int value, int amount, Type type) {
        return new ScreenValue(value - amount, type);
    }

    /**
     * A value calculated by a {@link ScreenBuilder}.
     */
    public class ScreenValue {
        private final int value;
        private final Type type;
        private boolean applied;

        private ScreenValue(int value, Type type) {
            this.value = value;
            this.type = type;
        }

        /**
         * Applies this value to the builder, and returns the value that was applied.
         */
        public int apply() {
            if (!applied) {
                switch (type) {
                    case WIDTH -> currentWidth = value;
                    case HEIGHT -> currentHeight = value;
                    case RENDER_WIDTH -> renderWidth = value;
                    case RENDER_HEIGHT -> renderHeight = value;
                }

                applied = true;
            }

            return value;
        }

        /**
         * @return this value without applying it to the builder.
         */
        public int pop() {
            return value;
        }
    }

    /**
     * Stores different types of values for the screen.
     */
    enum Type {
        WIDTH,
        HEIGHT,
        RENDER_WIDTH,
        RENDER_HEIGHT
    }

    /**
     * Specifies a position of a screen.
     */
    enum ScreenPos {
        BOTTOM_CENTER,
        CENTER,
        TOP_CENTER,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        TOP_RIGHT
    }
}