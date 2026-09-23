package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.mixin.accessor.ScreenInvoker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.getFont;

/**
 * Makes building screens easier.
 *
 * @see BasicDillonLibScreen
 * @see DillonLibMenuScreen
 * @see WidgetData
 * @since 1.2
 */
@Dill(DillType.CLIENT)
public class ScreenBuilder {
    private final Screen screen;
    private final Positions positions;

    private ScreenBuilder(Screen screen) {
        this.screen = screen;
        this.positions = new Positions();
    }

    /**
     * Creates a new {@code ScreenBuilder}.
     *
     * @param screen the screen to build on.
     * @return a new screen builder.
     */
    public static ScreenBuilder of(Screen screen) {
        return new ScreenBuilder(screen);
    }

    /**
     * Creates a new {@code ScreenBuilder} with a specific position.
     *
     * @param screen the screen to build on.
     * @param pos    the position.
     * @return a screen builder.
     */
    private static ScreenBuilder ofPos(Screen screen, ScreenPos pos) {
        ScreenBuilder builder = of(screen);

        builder.width(pos, Type.WIDTH).apply();
        builder.height(pos, Type.HEIGHT).apply();

        builder.positions.initialWidth = builder.getCurrentWidth();
        builder.positions.initialHeight = builder.getCurrentHeight();

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
        return positions.screenWidth;
    }

    /**
     * @return the current height for this builder.
     */
    private int getCurrentHeight() {
        return positions.screenHeight;
    }

    /**
     * Captures the width in its current place.
     */
    public int captureWidth() {
        return new ScreenValue(positions.screenWidth, Type.WIDTH).pop();
    }

    /**
     * Captures the height in its current place.
     */
    public int captureHeight() {
        return new ScreenValue(positions.screenHeight, Type.HEIGHT).pop();
    }

    /**
     * Captures the graphics width in its current place.
     */
    public int captureGraphicsWidth() {
        return new ScreenValue(positions.graphicsWidth, Type.GRAPHICS_WIDTH).pop();
    }

    /**
     * Captures the graphics height in its current place.
     */
    public int captureGraphicsHeight() {
        return new ScreenValue(positions.graphicsHeight, Type.GRAPHICS_HEIGHT).pop();
    }

    /**
     * @return the default offset for modifying width and height.
     */
    public int defaultOffset() {
        return 24;
    }

    /**
     * @return the default render offset for modifying graphics width and graphics height.
     */
    public int defaultGraphicsOffset() {
        return 20;
    }

    /**
     * Resets the width and height to the initial values.
     */
    public void resetBounds() {
        positions.screenWidth = positions.initialWidth;
        positions.screenHeight = positions.initialHeight;
    }

    /**
     * Resets the render bounds to the initial values.
     */
    public void resetRenderBounds() {
        positions.graphicsWidth = positions.initialWidth;
        positions.graphicsHeight = positions.initialHeight;
    }

    /**
     * @return the center pos for text.
     */
    private int getTextCenter() {
        return width(ScreenPos.CENTER, Type.WIDTH).pop();
    }

    /**
     * Draws title text on a screen.
     */
    public ScreenValue textTitleGraphicsHeight(GuiGraphicsExtractor graphics) {
        return textTitleGraphicsHeight(graphics, screen.getTitle());
    }

    /**
     * Draws custom title text on a screen.
     */
    public ScreenValue textTitleGraphicsHeight(GuiGraphicsExtractor graphics, Component title) {
        int height = graphicsHeightTop().pop() + graphicsHeightDown(13).pop();
        return textCenterGraphicsHeight(graphics, title, height);
    }

    /**
     * Draws text and then moves the height down.
     */
    public ScreenValue textCenterAndGraphicsHeightDown(GuiGraphicsExtractor graphics, Component text) {
        return textCenterAndGraphicsHeightDown(graphics, text, defaultGraphicsOffset());
    }

    /**
     * Draws text and then moves the height down by a specific amount.
     */
    public ScreenValue textCenterAndGraphicsHeightDown(GuiGraphicsExtractor graphics, Component text, int amount) {
        textCenterGraphicsHeight(graphics, text);
        return graphicsHeightDown(amount);
    }

    /**
     * Draws text in a centered fashion.
     */
    public ScreenValue textCenterGraphicsHeight(GuiGraphicsExtractor graphics, Component text) {
        return textCenterGraphicsHeight(graphics, text, positions.graphicsHeight);
    }

    /**
     * Draws text in a centered fashion at a specific height.
     */
    public ScreenValue textCenterGraphicsHeight(GuiGraphicsExtractor graphics, Component text, int height) {
        int position = getTextCenter() - getFont().width(text) / 2;

        graphics.text(
                screen.getFont(),
                text,
                position,
                height,
                CommonColors.WHITE
        );

        return new ScreenValue(position, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Draws wrapped text centered and moves the height down after each line.
     */
    public ScreenValue textCenterAndGraphicsHeightDownWrapped(GuiGraphicsExtractor graphics, Component text) {
        return textCenterAndGraphicsHeightDownWrapped(graphics, text, 400);
    }

    /**
     * Draws wrapped text centered and moves the height down after each line, with a custom max width.
     */
    public ScreenValue textCenterAndGraphicsHeightDownWrapped(GuiGraphicsExtractor graphics, Component text, int maxWidth) {
        return textCenterAndGraphicsHeightDownWrapped(graphics, text, maxWidth, defaultGraphicsOffset());
    }

    /**
     * Draws wrapped text centered and moves the height down by a specific amount after each line, with a custom max width and amount for each line.
     */
    public ScreenValue textCenterAndGraphicsHeightDownWrapped(GuiGraphicsExtractor graphics, Component text, int maxWidth, int amount) {
        List<FormattedCharSequence> lines = getFont().split(text, maxWidth);

        for (FormattedCharSequence line : lines) {
            int position = getTextCenter() - getFont().width(line) / 2;

            graphics.text(
                    screen.getFont(),
                    line,
                    position,
                    positions.graphicsHeight,
                    CommonColors.WHITE
            );

            graphicsHeightDown(amount).apply();
        }

        return new ScreenValue(positions.graphicsHeight, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Adds a centered multi-line text widget and then moves the height down.
     */
    public ScreenValue multiLineTextCenterAndHeightDown(Component text) {
        return multiLineTextCenterAndHeightDown(text, 400);
    }

    /**
     * Adds a centered multi-line text widget with a custom max width and then moves the height down.
     */
    public ScreenValue multiLineTextCenterAndHeightDown(Component text, int maxWidth) {
        return multiLineTextCenterAndHeightDown(text, maxWidth, defaultGraphicsOffset());
    }

    /**
     * Adds a centered multi-line text widget with a custom max width and height offset.
     */
    public ScreenValue multiLineTextCenterAndHeightDown(Component text, int maxWidth, int amount) {
        MultiLineTextWidget widget = new MultiLineTextWidget(positions.screenWidth, positions.screenHeight, text, getFont())
                .setMaxWidth(maxWidth)
                .setCentered(true);
        ((ScreenInvoker) screen).addRenderableModWidget(widget);
        return heightDown(widget.getHeight() + amount);
    }

    /**
     * @param pos the screen position.
     * @return the width at a specific screen position.
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
        return valueAdd(positions.screenWidth, amount, Type.WIDTH);
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
        return valueNegate(positions.screenWidth, amount, Type.WIDTH);
    }

    /**
     * @param widget the widget to reset graphics width to.
     * @return the graphics width at the widget's position.
     */
    public ScreenValue graphicsWidth(AbstractWidget widget) {
        return new ScreenValue(widget.getX(), Type.GRAPHICS_WIDTH);
    }

    /**
     * Calculates the graphics width at the center, and returns the new value.
     */
    public ScreenValue graphicsWidthCenter() {
        return width(ScreenPos.CENTER, Type.GRAPHICS_WIDTH);
    }

    /**
     * Calculates the graphics width at the bottom left, and returns the new value.
     */
    public ScreenValue graphicsWidthSideLeft() {
        return width(ScreenPos.BOTTOM_LEFT, Type.GRAPHICS_WIDTH);
    }

    /**
     * Calculates the graphics width at the bottom right, and returns the new value.
     */
    public ScreenValue graphicsWidthSideRight() {
        return width(ScreenPos.BOTTOM_RIGHT, Type.GRAPHICS_WIDTH);
    }

    /**
     * Calculates the current graphics width moved right by {@link ScreenBuilder#defaultGraphicsOffset()}, and returns the new value.
     */
    public ScreenValue graphicsWidthRight() {
        return graphicsWidthRight(defaultGraphicsOffset());
    }

    /**
     * Calculates the current graphics width moved right, and returns the new value.
     */
    public ScreenValue graphicsWidthRight(int amount) {
        return valueAdd(positions.graphicsWidth, amount, Type.GRAPHICS_WIDTH);
    }

    /**
     * Calculates the current graphics width moved left by {@link ScreenBuilder#defaultGraphicsOffset()}, and returns the new value.
     */
    public ScreenValue graphicsWidthLeft() {
        return graphicsWidthLeft(defaultGraphicsOffset());
    }

    /**
     * Calculates the current graphics width moved left, and returns the new value.
     */
    public ScreenValue graphicsWidthLeft(int amount) {
        return valueNegate(positions.graphicsWidth, amount, Type.GRAPHICS_WIDTH);
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
        return valueAdd(positions.screenHeight, amount, Type.HEIGHT);
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
        return valueNegate(positions.screenHeight, amount, Type.HEIGHT);
    }

    /**
     * @param widget the widget to reset graphics height to.
     * @return the graphics height at the widget's position.
     */
    public ScreenValue graphicsHeight(AbstractWidget widget) {
        return new ScreenValue(widget.getY(), Type.GRAPHICS_HEIGHT);
    }

    /**
     * Calculates the graphics height at the bottom center, and returns the new value.
     */
    public ScreenValue graphicsHeightBottom() {
        return height(ScreenPos.BOTTOM_CENTER, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Calculates the graphics height at the center, and returns the new value.
     */
    public ScreenValue graphicsHeightCenter() {
        return height(ScreenPos.CENTER, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Calculates the graphics height at the top center, and returns the new value.
     */
    public ScreenValue graphicsHeightTop() {
        return height(ScreenPos.TOP_CENTER, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Calculates the current graphics height moved down by {@link ScreenBuilder#defaultGraphicsOffset()}, and returns the new value.
     */
    public ScreenValue graphicsHeightDown() {
        return graphicsHeightDown(defaultGraphicsOffset());
    }

    /**
     * Calculates the current graphics height moved down, and returns the new value.
     */
    public ScreenValue graphicsHeightDown(int amount) {
        return valueAdd(positions.graphicsHeight, amount, Type.GRAPHICS_HEIGHT);
    }

    /**
     * Calculates the current graphics height moved up by {@link ScreenBuilder#defaultOffset()}, and returns the new value.
     */
    public ScreenValue graphicsHeightUp() {
        return graphicsHeightUp(defaultGraphicsOffset());
    }

    /**
     * Calculates the current graphics height moved up, and returns the new value.
     */
    public ScreenValue graphicsHeightUp(int amount) {
        return valueNegate(positions.graphicsHeight, amount, Type.GRAPHICS_HEIGHT);
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
                    case WIDTH -> positions.screenWidth = value;
                    case HEIGHT -> positions.screenHeight = value;
                    case GRAPHICS_WIDTH -> positions.graphicsWidth = value;
                    case GRAPHICS_HEIGHT -> positions.graphicsHeight = value;
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
        GRAPHICS_WIDTH,
        GRAPHICS_HEIGHT
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