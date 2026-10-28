package net.dillon.quesoexample.helper;

import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.CommonWidgets;
import net.dillon.quesoexample.screen.QuesoExampleMenuScreen;
import net.dillon.quesoexample.screen.QuesoExampleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class ModHelper {

    public static UpdatableSpriteButton menuButton(String letter, boolean shouldUpdate, boolean menuScreen) {
        return CommonWidgets.createSmallUpdatableSpriteButton(
                letter,
                Component.literal("QuesoExample"),
                () -> shouldUpdate,
                Component.literal("YAY"),
                Identifier.fromNamespaceAndPath("quesoexamplemod", "qoq"),
                b -> {
                    openScreen(menuScreen ? new QuesoExampleMenuScreen(null) : new QuesoExampleScreen());
                }
        );
    }
}