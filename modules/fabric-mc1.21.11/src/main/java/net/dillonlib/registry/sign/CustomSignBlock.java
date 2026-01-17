package net.dillonlib.registry.sign;

import net.dillonlib.annotation.GlobalUse;
import net.dillonlib.registry.DLR;
import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code sign block.}
 */
@GlobalUse
public class CustomSignBlock extends SignBlock {

    public CustomSignBlock(Identifier woodTypeId, Settings settings) {
        super(DLR.registerDefaultWoodType(woodTypeId), settings);
    }
}