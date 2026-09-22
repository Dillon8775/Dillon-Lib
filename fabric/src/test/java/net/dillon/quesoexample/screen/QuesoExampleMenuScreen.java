package net.dillon.quesoexample.screen;

import net.dillon.dillonlib.screen.ConditionalTooltip;
import net.dillon.dillonlib.screen.DillonLibMenuScreen;
import net.dillon.dillonlib.screen.ScreenBuilder;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.dillon.quesoexample.platform.QuesoExamplePlatformGetter;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class QuesoExampleMenuScreen extends DillonLibMenuScreen {

    public QuesoExampleMenuScreen(Screen lastScreen) {
        super(lastScreen, Component.literal("Testing Screen!"), ScreenBuilder::ofTopCentered);
    }

    @Override
    public void widgets() {
        this.addRenderableWidget(button());
        builder().widthRight().apply();
        this.addRenderableWidget(button());
    }

    private AbstractWidget button() {
        return this.createWidget(
                Button.builder(Texts.BLANK, b -> {
                        })
                        .bounds(builder().captureWidth(), builder().captureHeight(), 20, 20)
                        .build(),
                () -> false,
                () -> ConditionalTooltip.of(
                        Component.literal("this is ON tooltip!"),
                        Component.literal("this is OFF tooltip!")
                )
        );
    }

    @Override
    protected void renderModInfo(GuiGraphicsExtractor graphics) {
        ClientTasks.drawModInfo(
                graphics,
                this,
                Component.literal(QuesoExamplePlatformGetter.get().modVersion()).withStyle(ChatFormatting.RED),
                Identifier.fromNamespaceAndPath("quesoexamplemod", "test_icon"),
                false
        );
    }
}