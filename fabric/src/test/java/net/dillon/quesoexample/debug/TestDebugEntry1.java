package net.dillon.quesoexample.debug;

import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

public class TestDebugEntry1 extends DillonLibDebugEntry {

    @Override
    public void display(DebugScreenDisplayer displayer, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        displayer.addToGroup(TestDebugKeysAndGroups.TEST_ONE, "okay1");
        displayer.addToGroup(TestDebugKeysAndGroups.TEST_ONE, "okay2");
        displayer.addToGroup(TestDebugKeysAndGroups.TEST_ONE, "okay3");

        displayer.addFactToGroup(TestDebugKeysAndGroups.TEST_ONE, "yes this is a fact", debugFact -> debugFact.value("ok"));
    }
}