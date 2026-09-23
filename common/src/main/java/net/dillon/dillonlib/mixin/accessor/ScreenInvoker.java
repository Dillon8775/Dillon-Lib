package net.dillon.dillonlib.mixin.accessor;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Invoker for screen methods.
 * @since 1.2
 */
@Mixin(Screen.class)
public interface ScreenInvoker {
    @Invoker("addRenderableWidget")
    <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableModWidget(T widget);

    @Invoker("addWidget")
    <T extends GuiEventListener & NarratableEntry> T addModWidget(T widget);

    @Invoker("removeWidget")
    void removeModWidget(GuiEventListener widget);

    @Invoker("repositionElements")
    void refreshWidgets();
}