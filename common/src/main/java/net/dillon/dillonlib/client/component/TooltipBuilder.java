package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

/**
 * A representation of a button builder class.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public interface TooltipBuilder<T> {
    T tooltip(Component tooltip);
    T tooltip(Supplier<Component> tooltip);
}