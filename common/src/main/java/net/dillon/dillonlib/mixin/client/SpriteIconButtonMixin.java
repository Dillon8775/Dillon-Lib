package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.client.impl.SpriteIconButtonImpl;
import net.minecraft.client.gui.components.SpriteIconButton;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SpriteIconButton.class)
public class SpriteIconButtonMixin implements SpriteIconButtonImpl {
    @Shadow @Final @Mutable
    protected int spriteWidth;
    @Shadow @Final @Mutable
    protected int spriteHeight;

    @Override
    public void setSpriteSize(int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }
}