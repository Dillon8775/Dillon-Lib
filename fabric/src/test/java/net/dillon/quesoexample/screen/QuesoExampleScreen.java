package net.dillon.quesoexample.screen;

import net.dillon.dillonlib.screen.BasicDillonLibScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class QuesoExampleScreen extends BasicDillonLibScreen {

    public QuesoExampleScreen() {
        super(Component.literal("Testing Screen!"));
    }

    @Override
    protected void drawGraphics(GuiGraphicsExtractor graphics) {
        builder().graphicsHeightTop().apply();
        builder().graphicsHeightDown(40).apply();

        builder().textCenterAndGraphicsHeightDown(graphics, Component.literal("Woah!")).apply();
        builder().textCenterAndGraphicsHeightDown(graphics, Component.literal("Yay!")).apply();
        builder().textCenterAndGraphicsHeightDown(graphics, Component.literal("WOOOOOOOOO"), 40).apply();
        builder().textCenterAndGraphicsHeightDown(graphics, Component.literal("ok!")).apply();
    }

    @Override
    public void widgets() {
    }
}