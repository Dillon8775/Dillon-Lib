package net.dillonlib.registry.sign.wall;

import net.dillonlib.annotation.GlobalUse;
import net.dillonlib.registry.DLR;
import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall sign block.}
 */
@GlobalUse
public class CustomWallSignBlock extends WallSignBlock {

	public CustomWallSignBlock(Identifier woodTypeId, Settings settings) {
		super(DLR.registerDefaultWoodType(woodTypeId), settings);
	}
}