package net.dillon.lib.registry.sign.wall;

import net.dillon.lib.annotation.GlobalUse;
import net.dillon.lib.registry.DillonsRegistry;
import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall sign block.}
 */
@GlobalUse
public class CustomWallSignBlock extends WallSignBlock {

	public CustomWallSignBlock(Identifier woodTypeId, Settings settings) {
		super(DillonsRegistry.registerDefaultWoodType(woodTypeId), settings);
	}
}