package net.dillon.lib;

import net.dillon.lib.client.render.RendererRegistry;
import net.dillon.lib.registry.DillonsCRegistry;
import net.fabricmc.api.ClientModInitializer;

public class DillonLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DillonsCRegistry.init();

		RendererRegistry.registerCutoutBlocks();
		RendererRegistry.registerBoatRenderers();
	}
}