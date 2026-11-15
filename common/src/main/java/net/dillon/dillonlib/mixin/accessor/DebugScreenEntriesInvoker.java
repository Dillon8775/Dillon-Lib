package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Accessor for registering debug entry keys.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
@Mixin(DebugScreenEntries.class)
public interface DebugScreenEntriesInvoker {
    @Invoker("key")
    static ResourceKey<DebugScreenEntry> key(String name) {
        throw new AssertionError();
    }
}