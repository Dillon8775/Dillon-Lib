package net.dillon.quesoexample.screen;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.quesoexample.platform.QuesoExamplePlatformGetter;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class QuesoExampleScreen extends OptionsSubScreen {

    public QuesoExampleScreen(Screen lastScreen) {
        super(lastScreen, Minecraft.getInstance().options, Component.literal("Testing Screen!"));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float deltaTicks) {
        ClientTasks.drawModInfo(
                graphics,
                this,
                Component.literal(QuesoExamplePlatformGetter.get().modVersion()).withStyle(ChatFormatting.RED),
                Identifier.fromNamespaceAndPath("quesoexamplemod", "test_icon"),
                false
        );
    }

    @Override
    protected void addOptions() {
    }
}