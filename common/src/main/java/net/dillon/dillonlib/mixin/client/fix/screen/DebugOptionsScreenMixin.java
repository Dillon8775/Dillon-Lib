package net.dillon.dillonlib.mixin.client.fix.screen;

import net.dillon.dillonlib.util.DebugOptionsScreenImpl;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.debug.DebugOptionsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

/**
 * Fixes a bug when closing the debug options screen always sets the parent screen to {@code null}.
 */
@Mixin(DebugOptionsScreen.class)
public class DebugOptionsScreenMixin extends Screen implements DebugOptionsScreenImpl {
    @Unique
    private Screen parent = null;

    public DebugOptionsScreenMixin(Component title) {
        super(title);
    }

    @Override
    public void setParent(Screen parent) {
        this.parent = parent;
    }

    @Override
    public Screen getParent() {
        return this.parent;
    }

    @Override
    public void onClose() {
        if (this.parent != null) {
            openScreen(this.parent);
        } else {
            super.onClose();
        }
    }
}