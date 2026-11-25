package net.dillon.quesoexample.debug;

import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;

public abstract class DillonLibDebugEntry implements DebugScreenEntry {

    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }

    @Override
    public DebugEntryCategory category() {
        return TestDebugKeysAndGroups.TEST_CATEGORY;
    }
}