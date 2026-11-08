package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.mixin.accessor.ScreenInvoker;
import net.minecraft.client.gui.layouts.*;
import net.minecraft.client.gui.screens.Screen;

/**
 * A helper class for utilizing {@link GridLayout}.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class GridBuilder {
    public static final int DEFAULT_COLUMN_WIDTH = 2;
    private final Screen screen;
    private final GridLayout grid;
    private final GridLayout.RowHelper rowHelper;
    private final int columns;
    private float horizontalAlignment = getCenterAlignment();
    private float verticalAlignment = getCenterAlignment();

    private GridBuilder(Screen screen) {
        this(screen, DEFAULT_COLUMN_WIDTH);
    }

    private GridBuilder(Screen screen, int columns) {
        this.screen = screen;
        this.grid = new GridLayout();
        this.columns = columns;
        this.rowHelper = this.grid.createRowHelper(columns);
    }

    /**
     * Creates a new {@link GridBuilder} with the default column layout.
     */
    public static GridBuilder create(Screen screen) {
        return new GridBuilder(screen);
    }

    /**
     * Creates a new {@link GridBuilder} with a custom column layout.
     */
    public static GridBuilder create(Screen screen, int columns) {
        return new GridBuilder(screen, columns);
    }

    /**
     * @return a horizontal row.
     */
    public static LinearLayout createRow() {
        return createRow(8);
    }

    /**
     * @return a horizontal row with custom spacing.
     */
    public static LinearLayout createRow(int spacing) {
        return LinearLayout.horizontal().spacing(spacing);
    }

    /**
     * @return a vertical row.
     */
    public static LinearLayout createColumn() {
        return createColumn(8);
    }

    /**
     * @return a vertical row with custom spacing.
     */
    public static LinearLayout createColumn(int spacing) {
        return LinearLayout.vertical().spacing(spacing);
    }

    /**
     * @return the center alignment for a {@link GridLayout}.
     */
    public static float getCenterAlignment() {
        return 0.5F;
    }

    /**
     * Sets the row spacing for this builder.
     */
    public GridBuilder setRowSpacing(int rowSpacing) {
        this.grid.rowSpacing(rowSpacing);
        return this;
    }

    /**
     * Sets the column spacing for this builder.
     */
    public GridBuilder setColumnSpacing(int columnSpacing) {
        this.grid.columnSpacing(columnSpacing);
        return this;
    }

    /**
     * Sets the {@code X} alignment for this builder.
     */
    public GridBuilder setHorizontalAlignment(float horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    /**
     * Sets the {@code Y} alignment for this builder.
     */
    public GridBuilder setVerticalAlignment(float verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    /**
     * @return the {@link GridLayout} for this helper.
     */
    public GridLayout getGrid() {
        return this.grid;
    }

    /**
     * @return the {@link GridLayout.RowHelper} for this helper.
     */
    public GridLayout.RowHelper getCurrentRow() {
        return this.rowHelper;
    }

    /**
     * @return a new layout setting, aligning horizontally centered.
     */
    public LayoutSettings alignHorizontallyCenter() {
        return grid.newCellSettings().alignHorizontallyCenter();
    }

    /**
     * @return a new layout setting, aligning vertically in the middle.
     */
    public LayoutSettings alignVerticallyMiddle() {
        return grid.newCellSettings().alignVerticallyMiddle();
    }

    /**
     * Adds an element to the center of a row helper based on a column size of {@code 2}.
     */
    public <T extends LayoutElement> void fillRowAndCenter(T child) {
        fillRow(child, alignHorizontallyCenter());
    }

    /**
     * Adds a child and fills the current row to the {@link GridLayout.RowHelper}.
     */
    public <T extends LayoutElement> void fillRow(T child, LayoutSettings layoutSettings) {
        rowHelper.addChild(
                child,
                columns,
                layoutSettings
        );
    }

    /**
     * Centers the grid in the {@code direct middle} of the screen.
     */
    public void buildAndCenter() {
        buildAndAlign(() -> FrameLayout.centerInRectangle(grid, screen.getRectangle()));
    }

    /**
     * Aligns the grid at the {@code X} and {@code Y} positions.
     */
    public void buildAndAlign() {
        buildAndAlign(() -> FrameLayout.alignInRectangle(grid, screen.getRectangle(), horizontalAlignment, verticalAlignment));
    }

    /**
     * Aligns the grid somewhere.
     */
    public void buildAndAlign(Runnable runnable) {
        grid.arrangeElements();
        runnable.run();
        grid.visitWidgets(widget -> ((ScreenInvoker)screen).addRenderableModWidget(widget));
    }
}