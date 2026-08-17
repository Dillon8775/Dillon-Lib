package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Invoker for accessing the keybinds list in {@code KeybindsScreen}.
 * @since 1.1
 */
@Dill(DillType.CLIENT)
@Mixin(KeyBindsScreen.class)
public interface KeyBindsScreenAccessor {
    @Accessor("keyBindsList")
    KeyBindsList getKeyBindsList();
}