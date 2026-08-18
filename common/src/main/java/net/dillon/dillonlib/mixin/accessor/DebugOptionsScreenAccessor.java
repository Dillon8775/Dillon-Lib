package net.dillon.dillonlib.mixin.accessor;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.debug.DebugOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for the debug options screen {@link EditBox}.
 * @since 1.1
 */
@Mixin(DebugOptionsScreen.class)
public interface DebugOptionsScreenAccessor {
    @Accessor("searchBox")
    EditBox getSearchBox();
}