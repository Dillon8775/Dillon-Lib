package net.dillon.dillonlib.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    protected abstract int getHorizontalPosition(int currentButton, int numberOfButtons, int buttonWidth);
    @Unique
    private final List<SpriteIconButton> cachedButtons = new ArrayList<>();

    public TitleScreenMixin(Component title) {
        super(title);
    }

    /**
     * Increases the maximum amount of buttons that should be displayed on the title screen.
     */
    @Definition(id = "numberOfButtons", local = @Local(type = int.class, name = "numberOfButtons"))
    @Expression("numberOfButtons = ?")
    @Inject(method = "init", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    private void adjustAmountOfIconButtons(CallbackInfo ci, @Local(name = "numberOfButtons") LocalIntRef numberOfButtons) {
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {

            for (PlatformMenuButton data : clientModPlatform.menuButtons()) {
                if (data != null && data.titleCondition() && data.menuButton() != null) {
                    numberOfButtons.set(numberOfButtons.get() + 1);
                }
            }
        });
    }

    /**
     * Adds all {@link PlatformMenuButton}s to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void addButtonsAndWarning(CallbackInfo ci, int copyrightWidth, int copyrightX, int spacing, int topPos, int numberOfButtons, int currentButton, SpriteIconButton language, SpriteIconButton accessibility) {
        int[] b = {currentButton};

        cachedButtons.clear();
        PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {
            for (PlatformMenuButton data : clientModPlatform.menuButtons()) {
                if (data == null) {
                    return;
                }

                SpriteIconButton button = data.menuButton();
                if (data.titleCondition() && button != null) {
                    this.addRenderableWidget(button);
                    button.setPosition(this.getHorizontalPosition(++b[0], numberOfButtons, 20), topPos - 24);
                    data.consumer().accept(button);
                    cachedButtons.add(button);
                }
            }
        });
    }

    /**
     * Calls the horizontal position again so {@link PlatformMenuButton}s are placed correctly.
     */
    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;getHorizontalPosition(III)I"))
    private int replaceInlinedConstant(TitleScreen instance, int currentButton, int numberOfButtons, int buttonWidth, Operation<Integer> original, @Local(name = "numberOfButtons") int actualNumberOfButtons) {
        return original.call(instance, currentButton, actualNumberOfButtons, buttonWidth);
    }

    /**
     * Renders the update icon on top of any {@link PlatformMenuButton}.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void renderUpdateIcon(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        PlatformMenuButton.renderUpdateIcons(graphics, cachedButtons, true);
    }
}