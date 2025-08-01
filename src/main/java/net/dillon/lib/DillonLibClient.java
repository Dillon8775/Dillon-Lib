package net.dillon.lib;

import net.dillon.lib.client.render.RendererRegistry;
import net.dillon.lib.registry.DillonsCRegistry;
import net.fabricmc.api.ClientModInitializer;

/**
 * The client-side initializer for the DillonLib lirary mod.
 */
public class DillonLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DillonsCRegistry.init();

		RendererRegistry.registerCutoutBlocks();
		RendererRegistry.registerBoatRenderers();
	}
}