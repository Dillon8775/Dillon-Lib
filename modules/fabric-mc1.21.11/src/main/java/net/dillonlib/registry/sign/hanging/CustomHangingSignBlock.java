package net.dillonlib.registry.sign.hanging;

import net.dillonlib.annotation.GlobalUse;
import net.dillonlib.registry.DLR;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code hanging sign block.}
 */
@GlobalUse
public class CustomHangingSignBlock extends HangingSignBlock {

    public CustomHangingSignBlock(Identifier woodTypeId, Settings settings) {
        super(DLR.registerDefaultWoodType(woodTypeId), settings);
    }
}