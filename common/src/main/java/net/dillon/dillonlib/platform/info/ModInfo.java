package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.client.impl.FocusableTextWidgetImpl;
import net.dillon.dillonlib.client.impl.WidgetsImpl;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.util.UpdateChecker;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.Player;

import java.net.URI;

import static net.dillon.dillonlib.task.ClientTasks.getFont;
import static net.dillon.dillonlib.task.CommonTasks.sendSystemMessage;

/**
 * Stores mod information
 *
 * @since 1.3
 */
public class ModInfo {
    private final String id;
    private final String link;
    private final Component name;
    private final Component version;
    private final boolean hasUpdate;

    private ModInfo(String id, String link, Component name, Component version, boolean hasUpdate) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.version = version;
        this.hasUpdate = hasUpdate;
    }

    /**
     * Creates new {@link ModInfo}.
     */
    public static ModInfo create(String id, String link, Component name, Component version, boolean hasUpdate) {
        return new ModInfo(id, link, name, version, hasUpdate);
    }

    /**
     * Creates new {@link ModInfo} with the {@code link}, {@code version} and {@code update variable} already set.
     */
    public static ModInfo create(String id, String modrinthSlug, Component name) {
        return create(id, modrinthSlug, name, commonVersion(id));
    }

    /**
     * Creates new {@link ModInfo} with the link, name (specific color), version (specific color) and version already specified.
     */
    public static ModInfo create(String id, String modrinthSlug, Component name, ChatFormatting formatting) {
        return create(id, modrinthSlug, name.copy().withStyle(formatting), commonVersion(id).copy().withStyle(formatting));
    }

    /**
     * Creates new {@link ModInfo} with the {@code link} and {@code update variable} already set.
     */
    public static ModInfo create(String id, String modrinthSlug, Component name, Component version) {
        return create(
                id,
                "https://modrinth.com/mod/" + modrinthSlug,
                name,
                version,
                UpdateChecker.hasUpdate(
                        UpdateChecker.checkForUpdate(
                                modrinthSlug,
                                version.getString()
                        )
                )
        );
    }

    /**
     * @return a common mod version specified with a mod id.
     */
    public static Component commonVersion(String modId) {
        return Component.literal(Platforms.getUniversalPlatform().commonModVersion(modId));
    }

    /**
     * Creates a {@link FocusableTextWidget} with version text for a mod.
     */
    public AbstractWidget createVersionLabel(WidgetsImpl widgets) {
        FocusableTextWidget focusableTextWidget = FocusableTextWidget.builder(
                Component.translatable("dillonlib.version", version()),
                getFont()
        ).build();
        ((FocusableTextWidgetImpl) focusableTextWidget).setHasUpdate(hasUpdate());
        ((FocusableTextWidgetImpl) focusableTextWidget).setUpdateLink(link());

        return widgets.createWidget(
                focusableTextWidget,
                isActive -> {
                    if (hasUpdate()) {
                        return Component.translatable("dillonlib.update_available_message", name());
                    } else {
                        return Component.translatable("dillonlib.up_to_date");
                    }
                }
        );
    }

    /**
     * Sends a message to the player with white text color.
     */
    public void sendUpdateMessage(Player player) {
        sendUpdateMessage(player, TextColor.WHITE.getValue());
    }

    /**
     * Sends a message to the player indicating their mod requires an update.
     */
    public void sendUpdateMessage(Player player, int textColor) {
        if (!hasUpdate()) {
            return;
        }

        Component message = Component.translatable("dillonlib.update_available_message", name())
                .setStyle(
                        Style.EMPTY
                                .withHoverEvent(
                                        new HoverEvent.ShowText(Component.translatable("dillonlib.update_available_tooltip", name()))
                                )
                                .withClickEvent(
                                        new ClickEvent.OpenUrl(URI.create(link()))
                                ))
                .withColor(textColor);

        sendSystemMessage(player, message);
    }

    public String id() {
        return this.id;
    }

    public String link() {
        return this.link;
    }

    public Component name() {
        return this.name;
    }

    public Component version() {
        return this.version;
    }

    public boolean hasUpdate() {
        return this.hasUpdate;
    }
}