package net.dillon.dillonlib.mixin.client;

import com.mojang.blaze3d.platform.cursor.CursorTypes;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.impl.FocusableTextWidgetImpl;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.dillonlib.task.ClientTasks.getScreen;
import static net.dillon.dillonlib.task.ClientTasks.openLink;

@Dill(env = DillEnv.CLIENT)
@Mixin(FocusableTextWidget.class)
public class FocusableTextWidgetMixin extends MultiLineTextWidget implements FocusableTextWidgetImpl {
    @Unique
    private boolean hasUpdate = false;
    @Unique
    private String updateLink = "https://modrinth.com/mod/dillon-lib";

    public FocusableTextWidgetMixin(Component message, Font font) {
        super(message, font);
    }

    @Override
    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    @Override
    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClicked) {
        if (hasUpdate) {
            openLink(getScreen(), updateLink, false);
        }

        return super.mouseClicked(event, doubleClicked);
    }

    @Inject(method = "extractWidgetRenderState", at = @At("HEAD"))
    private void requestHandCursor(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (hasUpdate && isHovered()) {
            graphics.requestCursor(CursorTypes.POINTING_HAND);
        }
    }
}