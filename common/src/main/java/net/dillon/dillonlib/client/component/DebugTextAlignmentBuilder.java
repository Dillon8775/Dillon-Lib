package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.util.DebugTextAlignment;
import net.dillon.dillonlib.mixin.accessor.DebugGroupBuilderAccessor;
import net.minecraft.client.gui.components.debug.DebugColumn;
import net.minecraft.client.gui.components.debug.DebugGroup;
import net.minecraft.network.chat.Component;

import java.util.Optional;

/**
 * An instance of {@link DebugGroup.Builder}, with allows for easier column customization with the use of {@link DebugTextAlignment}.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class DebugTextAlignmentBuilder extends DebugGroup.Builder {

    public DebugTextAlignmentBuilder(Component title) {
        super(title);
    }

    /**
     * @return a new {@link DebugTextAlignmentBuilder}.
     */
    public static DebugTextAlignmentBuilder titled(Component title) {
        return new DebugTextAlignmentBuilder(title);
    }

    /**
     * Specifies the column to use for a debug group (pass in your mod's option).
     * @return this builder.
     */
    public DebugGroup.Builder withAutoColumn(DebugTextAlignment debugTextAlignment) {
        switch (debugTextAlignment) {
            case LEFT -> {
                return this.withPreferredColumn(DebugColumn.Side.LEFT);
            }
            case RIGHT -> {
                return this.withPreferredColumn(DebugColumn.Side.RIGHT);
            }
            default -> {
                ((DebugGroupBuilderAccessor)this).setPreferredColumn(Optional.empty());
                return this;
            }
        }
    }
}