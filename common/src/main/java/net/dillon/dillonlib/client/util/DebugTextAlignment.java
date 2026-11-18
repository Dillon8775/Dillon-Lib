package net.dillon.dillonlib.client.util;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.util.StringRepresentable;

/**
 * Enum class for determining side for mod debug text.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT, type = DillType.UNIVERSAL)
public enum DebugTextAlignment implements StringRepresentable {
    AUTO("Auto"),
    LEFT("Left"),
    RIGHT("Right");

    private final String name;

    DebugTextAlignment(final String name) {
        this.name = name;
    }

    public boolean auto() {
        return this == AUTO;
    }

    public boolean left() {
        return this == LEFT;
    }

    public boolean right() {
        return this == RIGHT;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}