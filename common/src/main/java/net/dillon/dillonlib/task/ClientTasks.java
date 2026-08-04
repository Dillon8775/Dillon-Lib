package net.dillon.dillonlib.task;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Util;

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
            minecraft.gui.hud.getChat().clearMessages(false);
        }
        if (minecraft.level != null) {
            minecraft.level.disconnect(ClientLevel.DEFAULT_QUIT_MESSAGE);
            minecraft.disconnect(new GenericMessageScreen(Component.translatable("menu.savingLevel")), false);
        }

        runnable.run();
    }

    /**
     * Opens a screen on the client.
     */
    public static void openScreen(Screen screen) {
        getMinecraft().gui.setScreen(screen);
    }

    /**
     * @return the current Minecraft screen.
     */
    public static Screen getScreen() {
        return getMinecraft().gui.screen();
    }

    /**
     * Sets the currnet screen to null.
     */
    public static void setNullScreen() {
        getMinecraft().gui.setScreen(null);
    }

    /**
     * @return the GUI width.
     */
    public static int getGuiWidth(GuiGraphicsExtractor context) {
        return context.guiWidth() / 2;
    }

    /**
     * @return the GUI height.
     */
    public static int getGuiHeight(GuiGraphicsExtractor context) {
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
    public static void drawTooltip(Component text, GuiGraphicsExtractor context, int mouseX, int mouseY) {
        Font font = getFont();
        context.setTooltipForNextFrame(font, font.split(text, 200), mouseX, mouseY);
    }

    /**
     * Draws a sprite.
     */
    public static void drawSprite(GuiGraphicsExtractor graphics, Identifier sprite, int xOffset, int yOffset, int width, int height) {
        drawSprite(graphics, sprite, xOffset, yOffset, width, height, 1.0F);
    }

    /**
     * Draws a small sprite over a button.
     */
    public static void drawSmallSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        drawSmallSprite(graphics, sprite, button, 1.0F);
    }

    /**
     * Draws a larger sprite over a button.
     */
    public static void drawLargeSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        drawLargeSprite(graphics, sprite, button, 1.0F);
    }

    /**
     * Draws a larger sprite over a button with a custom alpha.
     */
    public static void drawLargeSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button, float f) {
        drawSprite(graphics, sprite, button.getX() + 1, button.getY() + 1, 18, 18, f);
    }

    /**
     * Draws a small sprite over a button with a custom alpha.
     */
    public static void drawSmallSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button, float f) {
        drawSprite(graphics, sprite, button.getX() + 2, button.getY() + 2, 16, 16, f);
    }

    /**
     * Draws a sprite with a custom alpha value.
     */
    public static void drawSprite(GuiGraphicsExtractor graphics, Identifier sprite, int xOffset, int yOffset, int width, int height, float f) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, sprite, xOffset, yOffset, 0.0F, 0.0F, width, height, width, height, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Draws the update icon
     */
    public static void drawUpdateSprite(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("icon/trial_available"), x, y, 8, 8);
    }

    /**
     * Opens a link.
     */
    public static void openLink(Screen screen, String link, boolean trusted) {
        Minecraft minecraft = getMinecraft();

        minecraft.gui.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getPlatform().openUri(link);
            }
            minecraft.gui.setScreen(screen);
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