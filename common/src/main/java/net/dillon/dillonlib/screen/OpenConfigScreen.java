package net.dillon.dillonlib.screen;

import com.mojang.blaze3d.Blaze3D;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.gui.screens.Screen;

/**
 * Opens the configuration file for DillonLib.
 */
@Dill(DillType.CLIENT)
public class OpenConfigScreen extends BasicDillonLibScreen {

    public OpenConfigScreen(Screen parent) {
        super(Texts.BLANK);
    }

    @Override
    public void widgets() {
        Blaze3D.openPath(
                Platforms.getCommonPlatform().configDir()
                        .toFile().toPath().resolve("dillonlib.json")
        );
        this.onClose();
    }
}