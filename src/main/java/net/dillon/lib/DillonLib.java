package net.dillon.lib;

import net.dillon.lib.registry.DillonsRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DillonLib implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("Dillon Lib");
	public static boolean safeBoot;

	@Override
	public void onInitialize() {
		safeBoot = false;
		DillonsRegistry.init();
	}

	public static Identifier id(String path) {
		return Identifier.of("dillonlibtest", path);
	}

	/**
	 * The game is safe to run if the {@code safe} parameter returns true.
	 */
	public static void isSafe(boolean safe) {
		safeBoot = !safe;
	}

	/**
	 * Sends a message.
	 */
	public static void info(String message) {
		LOGGER.info(message);
	}

	/**
	 * Sends a {@code warning} message.
	 */
	public static void warn(String message) {
		LOGGER.warn(message);
	}

	/**
	 * Sends a {@code error} message.
	 */
	public static void error(String error) {
		LOGGER.error(error);
	}

	/**
	 * Sends a {@code debug} message.
	 */
	public static void debug(String message) {
		LOGGER.debug(message);
	}
}