package net.dillon.dillonlib.task;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Consumer;

/**
 * Client-side only methods and tasks that can be commonly used throughout your mod.
 * @since 1.0
 */
@Dill(DillType.CLIENT)
public class ClientTasks {

    /**
     * Stops and closes the game with a runnable.
     */
    public static void stop(Runnable runnable) {
        runnable.run();
        getMinecraft().stop();
    }

    /**
     * Disconnects from the current world with a runnable.
     */
    public static void disconnect(Runnable runnable) {
        Minecraft minecraft = getMinecraft();

        if (minecraft.gui != null) {
            minecraft.gui.getChat().clearMessages(false);
        }
        if (minecraft.level != null) {
            minecraft.level.disconnect();
        }

        runnable.run();
    }

    /**
     * Opens a screen on the client.
     */
    public static void openScreen(Screen screen) {
        getMinecraft().setScreen(screen);
    }

    /**
     * @return the current Minecraft screen.
     */
    public static Screen getScreen() {
        return getMinecraft().screen;
    }

    /**
     * Sets the currnet screen to null.
     */
    public static void setNullScreen() {
        getMinecraft().setScreen(null);
    }

    /**
     * @return the GUI width.
     */
    public static int getGuiWidth(GuiGraphics context) {
        return context.guiWidth() / 2;
    }

    /**
     * @return the GUI height.
     */
    public static int getGuiHeight(GuiGraphics context) {
        return context.guiHeight() - 20;
    }

    /**
     * @return Minecraft's current instance.
     */
    public static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    /**
     * @return the Minecraft's font.
     */
    public static Font getFont() {
        return getMinecraft().font;
    }

    /**
     * Draws a tooltip.
     */
    public static void drawTooltip(Component text, GuiGraphics context, int mouseX, int mouseY) {
        Font font = getFont();
        context.renderTooltip(font, font.split(text, 200), mouseX, mouseY);
    }

    /**
     * Draws a larger sprite over a button with a custom alpha.
     */
    public static void drawLargeSprite(GuiGraphics graphics, ResourceLocation sprite, Button button) {
        drawSprite(graphics, sprite, button.getX() + 1, button.getY() + 1, 18, 18);
    }

    /**
     * Draws a small sprite over a button with a custom alpha.
     */
    public static void drawSmallSprite(GuiGraphics graphics, ResourceLocation sprite, Button button) {
        drawSprite(graphics, sprite, button.getX() + 2, button.getY() + 2, 16, 16);
    }

    /**
     * Draws a sprite with a custom alpha value.
     */
    public static void drawSprite(GuiGraphics graphics, ResourceLocation sprite, int xOffset, int yOffset, int width, int height) {
        graphics.blit(sprite, xOffset, yOffset, 0.0F, 0.0F, width, height, width, height);
    }

    /**
     * Opens a link.
     */
    public static void openLink(Screen screen, String link, boolean trusted) {
        Minecraft minecraft = getMinecraft();

        minecraft.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getPlatform().openUri(link);
            }
            minecraft.setScreen(screen);
        }, link, trusted));
    }

    /**
     * Plays a sound to the {@link LocalPlayer}.
     */
    public static void playLocalSoundToLocalPlayer(SoundEvent sound) {
        playLocalSoundToLocalPlayer(sound, 1.0F, 1.0F);
    }

    /**
     * Plays a sound to the {@link LocalPlayer}, with a custom volume and pitch.
     */
    public static void playLocalSoundToLocalPlayer(SoundEvent sound, float volume, float pitch) {
        Minecraft minecraft = getMinecraft();

        if (minecraft.player != null) {
            minecraft.player.playSound(sound, volume, pitch);
        }
    }

    /**
     * Plays a sound to the client.
     */
    public static void playLocalSound(SoundEvent sound) {
        playLocalSound(sound, 1.0F, 1.0F);
    }

    /**
     * Plays a sound to the client, with a custom volume and pitch.
     */
    public static void playLocalSound(SoundEvent sound, float volume, float pitch) {
        getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(sound, pitch, volume));
    }

    /**
     * Executes a task for the {@link LocalPlayer} if the player isn't null.
     */
    public static void executeIfClientPlayer(Consumer<LocalPlayer> localPlayer) {
        LocalPlayer player = getMinecraft().player;

        if (player != null) {
            localPlayer.accept(player);
        }
    }

    /**
     * Executes a task of the current {@link ClientLevel} if it isn't null.
     */
    public static void executeIfClientLevel(Consumer<ClientLevel> clientLevel) {
        ClientLevel level = getMinecraft().level;

        if (level != null) {
            clientLevel.accept(level);
        }
    }
}