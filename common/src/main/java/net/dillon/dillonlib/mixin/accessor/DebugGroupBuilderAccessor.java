package net.dillon.dillonlib.mixin.accessor;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.gui.components.debug.DebugColumn;
import net.minecraft.client.gui.components.debug.DebugGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

/**
 * Accessor for setting the preferred column.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
@Mixin(DebugGroup.Builder.class)
public interface DebugGroupBuilderAccessor {
    @Accessor("preferredColumn")
    void setPreferredColumn(Optional<DebugColumn.Side> column);
}