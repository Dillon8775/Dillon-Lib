package net.dillon.dillonlib.screen;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Util;

/**
 * Opens the configuration file for DillonLib.
 */
@Dill(DillType.CLIENT)
public class OpenConfigScreen extends Screen {

    public OpenConfigScreen(Screen parent) {
        super(Texts.BLANK);
    }

    @Override
    public void init() {
        Util.getPlatform().openFile(
                Platforms.getCommonPlatform().configDir()
                        .resolve("dillonlib.json")
                        .toFile()
        );
        this.onClose();
    }
}