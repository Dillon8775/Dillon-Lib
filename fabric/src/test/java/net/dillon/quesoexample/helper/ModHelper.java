package net.dillon.quesoexample.helper;

import net.dillon.dillonlib.client.component.UpdatableSpriteButton;
import net.dillon.quesoexample.screen.QuesoExampleMenuScreen;
import net.dillon.quesoexample.screen.QuesoExampleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class ModHelper {

    public static UpdatableSpriteButton menuButton(String letter, String logo, boolean shouldUpdate, boolean menuScreen) {
        return UpdatableSpriteButton.builder(
                letter,
                Component.literal("QuesoExample"),
                Identifier.fromNamespaceAndPath("quesoexamplemod", logo),
                b -> {
                    openScreen(menuScreen ? new QuesoExampleMenuScreen(null) : new QuesoExampleScreen());
                }).updateSupplier(() -> shouldUpdate).tooltip(() -> Component.literal("YAY")).build();
    }
}