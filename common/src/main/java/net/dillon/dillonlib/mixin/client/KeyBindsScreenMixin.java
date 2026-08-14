package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.mixin.accessor.KeyBindsScreenAccessor;
import net.dillon.dillonlib.util.KeybindScrollHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyBindsScreen.class)
public abstract class KeyBindsScreenMixin extends OptionsSubScreen {

    public KeyBindsScreenMixin(Screen lastScreen, Options options, Component title) {
        super(lastScreen, options, title);
    }

    /**
     * Scrolls down to the pending category, to make accessing modded controls user-friendly.
     */
    @Override
    protected void init() {
        super.init();
        KeyMapping.Category category = KeybindScrollHelper.consume();
        if (category == null) {
            return;
        }

        KeyBindsScreen screen = (KeyBindsScreen) (Object) this;
        KeyBindsList list = ((KeyBindsScreenAccessor) screen).getKeyBindsList();

        if (list == null) {
            return;
        }

        int row = KeybindScrollHelper.findCategory(category);
        double scrollAmount = row * 20.0;
        list.setScrollAmount(scrollAmount);
    }
}