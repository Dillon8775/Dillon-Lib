package net.dillon.lib.registry.sign.hanging.wall;

import net.dillon.lib.annotation.GlobalUse;
import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall hanging sign block.}
 */
@GlobalUse
public class CustomWallHangingSignBlock extends WallHangingSignBlock {

	public CustomWallHangingSignBlock(Identifier woodTypeId, Settings settings) {
		super(DillonsRegistry.registerDefaultWoodType(woodTypeId), settings);
	}
}