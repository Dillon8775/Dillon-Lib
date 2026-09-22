package net.dillon.dillonlib.mixin.client;

import net.dillon.dillonlib.core.DillonLibOptions;
import net.dillon.dillonlib.core.DillonLibSounds;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.mixinplugin.Predicated;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import static net.dillon.dillonlib.task.ClientTasks.playLocalSound;

@Predicated
@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Makes all keybinding factories run and work.
     */
    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    private void implementKeyBindingFactories(CallbackInfo info) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            while (keyMapping.consumeClick()) {
                for (Map.Entry<KeyMapping, Consumer<LocalPlayer>> consumer : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.entrySet()) {
                    consumer.getValue().accept(Minecraft.getInstance().player);
                }
            }
        }
    }

    /**
     * The Fortnite Battle Pass.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void fortniteBattlePass(CallbackInfo ci) {
        if (!DillonLibOptions.getLibInstance().fortniteBattlePass) {
            return;
        }

        Random random = new Random();
        if (random.nextFloat() < 0.01F) {
            playLocalSound(DillonLibSounds.FORTNITE_BATTLE_PASS, 5.0F, 1.0F);
        }
    }
}