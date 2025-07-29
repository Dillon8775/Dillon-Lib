package net.dillon.lib.registry.sign;

import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code sign block.}
 */
public class CustomSignBlock extends SignBlock {

    public CustomSignBlock(Identifier woodTypeId, Settings settings) {
        super(DillonsRegistry.registerDefaultWoodType(woodTypeId), settings);
    }
}