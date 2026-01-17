package net.dillonlib.registry.sign.hanging.wall;

import net.dillonlib.annotation.GlobalUse;
import net.dillonlib.registry.DLR;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall hanging sign block.}
 */
@GlobalUse
public class CustomWallHangingSignBlock extends WallHangingSignBlock {

	public CustomWallHangingSignBlock(Identifier woodTypeId, Settings settings) {
		super(DLR.registerDefaultWoodType(woodTypeId), settings);
	}
}