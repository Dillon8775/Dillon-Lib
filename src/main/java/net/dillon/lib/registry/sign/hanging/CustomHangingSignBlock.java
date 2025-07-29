package net.dillon.lib.registry.sign.hanging;

import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code hanging sign block.}
 */
public class CustomHangingSignBlock extends HangingSignBlock {

    public CustomHangingSignBlock(Identifier woodTypeId, Settings settings) {
        super(DillonsRegistry.registerDefaultWoodType(woodTypeId), settings);
    }
}