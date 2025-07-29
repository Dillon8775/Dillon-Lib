package net.dillon.lib.client;

import net.dillon.lib.DillonLib;
import net.dillon.lib.registry.DillonsCRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class LibTestClient {
    public static final KeyBinding TEST_KEYBIND = DillonsCRegistry.registerKeyBinding("test", "test.test", GLFW.GLFW_KEY_U,
            () -> MinecraftClient.getInstance().worldRenderer.reload(), Text.translatable("lol.lol"));

    public static void init() {
        DillonLib.error("Initialized LibTestClient");
    }
}