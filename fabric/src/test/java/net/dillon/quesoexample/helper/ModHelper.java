package net.dillon.quesoexample.helper;

import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.quesoexample.screen.QuesoExampleMenuScreen;
import net.dillon.quesoexample.screen.QuesoExampleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Map;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class ModHelper {

    public static UpdatableSpriteButton menuButton(String letter, boolean shouldUpdate, boolean menuScreen) {
        return ClientTasks.createMenuButton(
                letter,
                Identifier.withDefaultNamespace(""),
                (button) -> {
                    openScreen(menuScreen ? new QuesoExampleMenuScreen(null) : new QuesoExampleScreen());
                },
                Map.of(
                        shouldUpdate,
                        Component.literal("YAY")
                ),
                Component.literal(letter),
                true
        );
    }
}