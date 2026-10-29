package net.dillon.dillonlib.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibOptions;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = TitleScreen.class, priority = 2000)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    protected abstract int getHorizontalPosition(int currentButton, int numberOfButtons, int buttonWidth);

    public TitleScreenMixin(Component title) {
        super(title);
    }

    /**
     * Increases the maximum number of buttons according to the {@code configure mods button}.
     */
    @Definition(id = "numberOfButtons", local = @Local(type = int.class, name = "numberOfButtons"))
    @Expression("numberOfButtons = ?")
    @Inject(method = "init", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    private void adjustAmountOfIconButtons(CallbackInfo ci, @Local(name = "numberOfButtons") LocalIntRef numberOfButtons) {
        if (!DillonLibOptions.getLibInstance().core().dillonsModsButton) {
            return;
        }

        numberOfButtons.set(numberOfButtons.get() + 1);
    }

    /**
     * Adds the {@code configure mods button} to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void addButtonsAndWarning(CallbackInfo ci, int copyrightWidth, int copyrightX, int spacing, int topPos, int numberOfButtons, int currentButton, SpriteIconButton language, SpriteIconButton accessibility) {
        if (!DillonLibOptions.getLibInstance().core().dillonsModsButton) {
            return;
        }

        UpdatableSpriteButton configureModsButton = DillonLibClient.configureModsButton(this);
        configureModsButton.setPosition(this.getHorizontalPosition(++currentButton, numberOfButtons, 20), topPos - 24);
        this.addRenderableWidget(configureModsButton);
    }
}