package net.dillon.dillonlib.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {

    public PauseScreenMixin(Component title) {
        super(title);
    }

    /**
     * Adds all {@link PlatformMenuButton}s to the pause screen.
     */
    @Definition(id = "integratedServer", local = @Local(type = IntegratedServer.class, name = "integratedServer"))
    @Expression("integratedServer = ?")
    @Inject(method = "createPauseMenu", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void addQualityOfQuesoButtons(CallbackInfo ci, @Local(name = "iconButtonRow") LinearLayout iconButtonRow) {
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {

            for (PlatformMenuButton data : clientModPlatform.menuButtons()) {
                if (data == null) {
                    return;
                }

                SpriteIconButton button = data.menuButton();
                if (data.pauseCondition() && button != null) {
                    SpriteIconButton b = this.addRenderableOnly(button);
                    iconButtonRow.addChild(b);
                }
            }
        });
    }
}