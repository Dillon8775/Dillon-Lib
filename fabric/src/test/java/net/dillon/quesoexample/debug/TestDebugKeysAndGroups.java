package net.dillon.quesoexample.debug;

import net.minecraft.ChatFormatting;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.gui.components.debug.DebugColumn;
import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugGroup;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class TestDebugKeysAndGroups {
    public static final ResourceKey<DebugScreenEntry> TEST_ENTRY_ONE = key("test_entry_one");
    public static final ResourceKey<DebugScreenEntry> TEST_ENTRY_TWO = key("test_entry_two");
    public static final ResourceKey<DebugScreenEntry> TEST_ENTRY_THREE = key("test_entry_three");
    public static final ResourceKey<DebugScreenEntry> TEST_ENTRY_FOUR = key("test_entry_four");

    public static final DebugGroup TEST_ONE = DebugGroup.Builder
            .titled(Component.literal("Ay").withStyle(ChatFormatting.BLUE))
            .withPreferredColumn(DebugColumn.Side.RIGHT)
            .withAccentColor(0xFF6A00)
            .build();
    public static final DebugGroup TEST_TWO = DebugGroup.Builder.titled("Way")
            .withPreferredColumn(DebugColumn.Side.RIGHT)
            .withAccentColor(0xFF6A00)
            .build();

    public static final DebugEntryCategory TEST_CATEGORY = new DebugEntryCategory(Component.literal("yayyy").withStyle(ChatFormatting.BOLD, ChatFormatting.UNDERLINE), 3.0F);

    private static ResourceKey<DebugScreenEntry> key(String name) {
        return ResourceKey.create(ClientRegistries.DEBUG_SCREEN_ENTRY, Identifier.fromNamespaceAndPath("quesoexamplemod", name));
    }
}