package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.util.DebugTextAlignment;
import net.dillon.dillonlib.mixin.accessor.DebugGroupBuilderAccessor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.debug.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * An instance of {@link DebugGroup.Builder}, with allows for easier column customization with the use of {@link DebugTextAlignment}.
 *
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class DebugBuilder extends DebugGroup.Builder {
    private static final Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> customProfiles = new HashMap<>();

    public DebugBuilder(Component title) {
        super(title);
    }

    /**
     * Creates a basic aligned debug group.
     */
    public static DebugGroup alignedDebugGroup(Component title, DebugTextAlignment debugTextAlignment) {
        return DebugBuilder
                .titled(title)
                .withAutoColumn(debugTextAlignment)
                .build();
    }

    /**
     * Creates a left-aligned if auto debug group.
     */
    public static DebugGroup leftAlignedDebugGroup(Component title, DebugTextAlignment debugTextAlignment) {
        return DebugBuilder
                .titled(title)
                .leftIfAuto(debugTextAlignment)
                .build();
    }

    /**
     * Creates a right-aligned if auto debug group.
     */
    public static DebugGroup rightAlignedDebugGroup(Component title, DebugTextAlignment debugTextAlignment) {
        return DebugBuilder
                .titled(title)
                .rightIfAuto(debugTextAlignment)
                .build();
    }

    /**
     * @return a basic debug category.
     */
    public static DebugEntryCategory basicDebugCategory(Component title) {
        return new DebugEntryCategory(title.copy().withStyle(ChatFormatting.BOLD, ChatFormatting.UNDERLINE), 3.0F);
    }

    /**
     * @return a new {@link DebugBuilder}.
     */
    public static DebugBuilder titled(Component title) {
        return new DebugBuilder(title);
    }

    /**
     * Creates a default profile upon game launch for modded debug huds.
     */
    public static void createCustomProfile(Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> profile) {
        customProfiles.putAll(profile);
    }

    /**
     * @return the map of custom profiles to be added to the default profile.
     */
    public static Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> getCustomProfiles() {
        return customProfiles;
    }

    /**
     * Specifies the left column if the option is auto for a debug group (pass in your mod's option).
     *
     * @return this builder.
     */
    public DebugGroup.Builder leftIfAuto(DebugTextAlignment debugTextAlignment) {
        if (debugTextAlignment.auto()) {
            return this.withPreferredColumn(DebugColumn.Side.LEFT);
        } else {
            return this.withPreferredColumn(DebugColumn.Side.RIGHT);
        }
    }

    /**
     * Specifies the right column if the option is auto a debug group (pass in your mod's option).
     *
     * @return this builder.
     */
    public DebugGroup.Builder rightIfAuto(DebugTextAlignment debugTextAlignment) {
        if (debugTextAlignment.auto()) {
            return this.withPreferredColumn(DebugColumn.Side.RIGHT);
        } else {
            return this.withPreferredColumn(DebugColumn.Side.LEFT);
        }
    }

    /**
     * Specifies the column to use for a debug group (pass in your mod's option).
     *
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
                ((DebugGroupBuilderAccessor) this).setPreferredColumn(Optional.empty());
                return this;
            }
        }
    }
}