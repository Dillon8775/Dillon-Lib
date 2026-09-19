package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.task.ClientTasks;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

/**
 * Similar to a {@link ModReference}, this holds a configuration library required for your mod, with the libName as a literal string to display if the mod isn't loaded.
 * @since 1.2
 * @see ClientTasks#tryOpenConfigScreen(Supplier, Component, ModConfigLib)
 */
public record ModConfigLib(Component libName, ModReference modReference) {
}