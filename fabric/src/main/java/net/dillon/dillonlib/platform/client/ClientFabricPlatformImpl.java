package net.dillon.dillonlib.platform.client;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.debug.*;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;

public class ClientFabricPlatformImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public void registerDebugEntries(WritableRegistry<DebugScreenEntry> registry) {
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_ONE, new TestDebugEntry1());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_TWO, new TestDebugEntry2());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_THREE, new TestDebugEntry3());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_FOUR, new TestDebugEntry4());
    }
}