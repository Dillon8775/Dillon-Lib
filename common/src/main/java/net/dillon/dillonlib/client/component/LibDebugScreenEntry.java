package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.resources.ResourceKey;

/**
 * Stores a {@link DebugScreenEntry} with its default status value.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.UNIVERSAL)
public record LibDebugScreenEntry(ResourceKey<DebugScreenEntry> entry, DebugScreenEntryStatus status) {

    public LibDebugScreenEntry(ResourceKey<DebugScreenEntry> entry) {
        this(entry, DebugScreenEntryStatus.NEVER);
    }
}