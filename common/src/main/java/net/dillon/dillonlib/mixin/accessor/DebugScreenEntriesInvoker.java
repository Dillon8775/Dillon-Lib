package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Invoker for registering debug entries to your mod.
 * @since 1.1
 */
@Dill(DillType.CLIENT)
@Mixin(DebugScreenEntries.class)
public interface DebugScreenEntriesInvoker {
    @Invoker("register")
    static Identifier invokeRegister(Identifier identifier, DebugScreenEntry entry) {
        throw new AssertionError();
    }
}