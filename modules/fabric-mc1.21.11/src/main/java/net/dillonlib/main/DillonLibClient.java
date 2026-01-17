package net.dillonlib.main;

import net.dillonlib.client.render.RendererRegistry;
import net.dillonlib.registry.DLRC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * The client-side initializer for the DillonLib lirary mod.
 */
@Environment(EnvType.CLIENT)
public class DillonLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DLRC.init();

		RendererRegistry.registerCutoutBlocks();
		RendererRegistry.registerBoatRenderers();
	}
}