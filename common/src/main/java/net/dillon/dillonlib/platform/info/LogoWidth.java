package net.dillon.dillonlib.platform.info;

/**
 * Determines the logo width, which determines where the logo is displayed on the options screen. This is very specific to Dillon's mods.
 * @since 1.0
 */
public enum LogoWidth {
    DEFAULT(50),
    PATCH(53),
    LONG_PATCH(55);

    private final int widthModifier;

    LogoWidth(int widthModifier) {
        this.widthModifier = widthModifier;
    }

    /**
     * @return the width modiifer for the logo width.
     */
    public int getWidthModifier() {
        return this.widthModifier;
    }
}