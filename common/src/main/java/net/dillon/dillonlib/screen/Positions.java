package net.dillon.dillonlib.screen;

/**
 * Captures screen positions.
 * @since 1.2
 * @see ScreenBuilder
 */
public class Positions {
    /// The first width and height that are initialized when creating a screen builder.
    /// These values never change once the screen is created.
    protected int initialWidth;
    protected int initialHeight;

    /// The current width and height of the screen builder.
    /// Modify these in builder methods, like init() or widgets().
    protected int currentWidth;
    protected int currentHeight;

    /// The current graphics width and graphics height of the screen builder, which is updated and then reset every frame to keep correct positions.
    /// Only modify these in render methods, like extractRenderState() and drawText().
    protected int graphicsWidth;
    protected int graphicsHeight;
}