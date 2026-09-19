package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.task.ClientTasks;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

/**
 * Similar to a {@link ModReference}, this holds a configuration library required for your mod, with the libName as a literal string to display if the mod isn't loaded.
 * @since 1.2
 * @see ClientTasks#tryOpenConfigScreen(Supplier, Component, ModConfigLib)
 */
public class ModConfigLib {
    private final Component libName;
    private final ModReference modReference;

    private ModConfigLib(Component libName, ModReference modReference) {
        this.libName = libName;
        this.modReference = modReference;
    }

    /**
     * Creates a new {@code ModConfigLib}.
     */
    public static ModConfigLib of(Component libName, ModReference modReference) {
        return new ModConfigLib(libName, modReference);
    }

    /**
     * @return the mod's {@code name}.
     */
    public Component name() {
        return this.libName;
    }

    /**
     * @return the mod's {@code mod reference}.
     */
    public ModReference reference() {
        return this.modReference;
    }
}