package net.dillon.dillonlib.platform.info;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.task.ClientTasks;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;

import java.util.List;
import java.util.function.Consumer;

/**
 * Stores menu button data.
 */
public record PlatformMenuButton(boolean titleCondition, boolean pauseCondition, boolean updateCondition, SpriteIconButton menuButton, Consumer<SpriteIconButton> consumer) {

    /**
     * Renders all update icons on any {@link PlatformMenuButton}.
     */
    public static void renderUpdateIcons(GuiGraphicsExtractor graphics, List<SpriteIconButton> addedButtons, boolean title) {
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {
            for (int i = 0; i < clientModPlatform.menuButtons().size(); i++) {
                PlatformMenuButton data = clientModPlatform.menuButtons().get(i);
                if (data == null) {
                    break;
                }

                try {
                    boolean bl = title ? data.titleCondition() : data.pauseCondition();
                    if (bl && data.updateCondition()) {
                        ClientTasks.renderUpdateIconOnButton(graphics, addedButtons.get(i), true);
                    }
                } catch (IndexOutOfBoundsException o) {
                    break;
                }
            }
        });
    }
}