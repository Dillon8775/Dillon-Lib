package net.dillon.dillonlib.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    @Unique
    private final List<SpriteIconButton> cachedButtons = new ArrayList<>();

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
        this.cachedButtons.clear();
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {

            int i = 0;
            for (PlatformMenuButton data : clientModPlatform.menuButtons()) {
                if (data == null) {
                    return;
                }

                if (i > 3) {
                    DillonLibMain.LOGGER.warn("Cannot add more than 3 menu buttons to pause menu!");
                    return;
                }

                SpriteIconButton button = data.menuButton();
                if (data.pauseCondition() && button != null) {
                    SpriteIconButton b = this.addRenderableOnly(button);
                    iconButtonRow.addChild(b);
                    cachedButtons.add(b);
                }

                i++;
            }
        });
    }

    /**
     * Renders the update icon on top of any {@link PlatformMenuButton}.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void renderUpdateIcon(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        PlatformMenuButton.renderUpdateIcons(graphics, cachedButtons, false);
    }
}