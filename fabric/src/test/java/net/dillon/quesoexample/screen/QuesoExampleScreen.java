package net.dillon.quesoexample.screen;

import net.dillon.dillonlib.screen.BasicDillonLibScreen;
import net.dillon.dillonlib.screen.ScreenBuilder;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class QuesoExampleScreen extends BasicDillonLibScreen {

    public QuesoExampleScreen() {
        super(Component.literal("Testing Screen!"), ScreenBuilder::ofTopCentered);
    }

    @Override
    protected void drawGraphics(GuiGraphicsExtractor graphics) {
        builder().renderHeight(40).apply();

        builder().textCenterAndHeightDown(graphics, Component.literal("Woah!")).apply();
        builder().textCenterAndHeightDown(graphics, Component.literal("Yay!")).apply();
        builder().textCenterAndHeightDown(graphics, Component.literal("WOOOOOOOOO"), 40).apply();
        builder().textCenterAndHeightDown(graphics, Component.literal("ok!")).apply();
    }

    @Override
    public void widgets() {
    }
}