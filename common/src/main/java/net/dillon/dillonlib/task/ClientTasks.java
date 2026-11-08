package net.dillon.dillonlib.task;

import com.mojang.blaze3d.Blaze3D;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.component.TextureBuilder;
import net.dillon.dillonlib.client.component.UpdatableSpriteButton;
import net.dillon.dillonlib.client.impl.DebugOptionsScreenImpl;
import net.dillon.dillonlib.core.DillonLibModConfigLibs;
import net.dillon.dillonlib.mixin.accessor.DebugOptionsScreenAccessor;
import net.dillon.dillonlib.platform.info.ModConfigLib;
import net.dillon.dillonlib.widespread.DillonsSprites;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.debug.DebugOptionsScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.CommonColors;

import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Client-side only methods and tasks that can be commonly used throughout your mod.
 * @since 1.0
 */
@Dill(env = DillEnv.CLIENT)
public class ClientTasks {

    /**
     * Stops and closes the game with a consumer.
     */
    public static void stop(Runnable runnable) {
        runnable.run();
        getMinecraft().stop();
    }

    /**
     * Disconnects from the current world with a consumer.
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
     * @return if the user has the {@code ALT} key pressed.
     */
    public static boolean hasAltDown() {
        return Minecraft.getInstance().hasAltDown();
    }

    /**
     * @return if the user has the {@code SHIFT} key pressed.
     */
    public static boolean hasShiftDown() {
        return Minecraft.getInstance().hasShiftDown();
    }

    /**
     * @return if the user has the {@code CTRL} key pressed.
     */
    public static boolean hasCtrlDown() {
        return Minecraft.getInstance().hasControlDown();
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
     * @return {@link Minecraft}'s current instance.
     */
    public static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    /**
     * @return Minecraft's {@link Font}.
     */
    public static Font getFont() {
        return getMinecraft().font;
    }

    /**
     * @return Minecraft's {@link SoundManager}.
     */
    public static SoundManager getSoundManager() {
        return getMinecraft().getSoundManager();
    }

    /**
     * @return the current Minecraft's {@link LocalPlayer}.
     */
    public static LocalPlayer getLocalPlayer() {
        return getMinecraft().player;
    }

    /**
     * Draws a tooltip.
     */
    public static void drawTooltip(Component text, GuiGraphicsExtractor context, int mouseX, int mouseY) {
        Font font = getFont();
        context.setTooltipForNextFrame(font, font.split(text, 200), mouseX, mouseY);
    }

    /**
     * Draws the update icon.
     */
    public static void drawUpdateSprite(GuiGraphicsExtractor graphics, int x, int y) {
        TextureBuilder.create(graphics, DillonsSprites.UPDATE_AVAILABLE, x, y, 8, 8).buildAndBlit(true);
    }

    /**
     * Draws a mod version and icon in a main menu screen.
     */
    public static void drawModInfo(GuiGraphicsExtractor graphics, Screen menuScreen, Component version, Identifier logo, boolean hasUpdate) {
        Font font = menuScreen.getFont();

        int textWidth = font.width(version);

        int rightMargin = 10;
        int textX = menuScreen.width - rightMargin - textWidth;
        int imageX = textX - 23;

        int textY = menuScreen.height - 21;
        int imageY = menuScreen.height - 26;

        graphics.text(font, version, textX, textY, CommonColors.WHITE, true);
        TextureBuilder.create(graphics, logo, imageX, imageY, 18, 18).buildAndBlit(true);

        if (hasUpdate) {
            drawUpdateSprite(graphics, imageX - 2, imageY - 2);
        }
    }

    /**
     * Tries to open a {@code YetAnotherConfigLib} screen, and warns the user if it's not.
     * @see ModConfigLib
     */
    public static void tryOpenYaclScreen(Supplier<Screen> configScreen, Component modName) {
        tryOpenConfigScreen(configScreen, modName, DillonLibModConfigLibs.YACL);
    }

    /**
     * Tries to open a {@code configuration} screen, if the config library mod is installed, and warns the user if it's not.
     * @see ModConfigLib
     */
    public static void tryOpenConfigScreen(Supplier<Screen> configScreen, Component modName, ModConfigLib lib) {
        Gui gui = Minecraft.getInstance().gui;

        if (!DillonLibModConfigLibs.isLibLoaded(lib)) {
            gui.toastManager().addToast(new SystemToast(
                    SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                    Component.translatable("dillonlib.toast.lib_required", lib.name())
                            .withStyle(ChatFormatting.RED),
                    Component.translatable("dillonlib.toast.download_lib", lib.name(), modName)));
        } else {
            gui.setScreen(configScreen.get());
        }
    }

    /**
     * Opens the {@link DebugOptionsScreen} with set text for the search bar.
     */
    public static void openDebugEntriesScreen(Screen parentScreen, String text) {
        openScreen(new DebugOptionsScreen());
        if (getScreen() instanceof DebugOptionsScreen debugOptionsScreen) {
            ((DebugOptionsScreenImpl)debugOptionsScreen).setParent(parentScreen);
            ((DebugOptionsScreenAccessor)debugOptionsScreen).getSearchBox().setValue(text);
        }
    }

    /**
     * Renders the update icon on a button with custom position modifiers.
     */
    public static void renderUpdateIconOnButton(GuiGraphicsExtractor graphics, UpdatableSpriteButton button) {
        if (button == null || !button.shouldRenderUpdateSprite().getAsBoolean()) {
            return;
        }

        drawUpdateSprite(graphics, button.getX() + (button.getWidth() - 5), button.getY() - 3);
    }

    /**
     * Opens a link.
     */
    public static void openLink(Screen screen, String link, boolean trusted) {
        Minecraft minecraft = getMinecraft();

        URI uri = URI.create(link);
        minecraft.gui.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Blaze3D.openUri(uri);
            }
            minecraft.gui.setScreen(screen);
        }, uri, trusted));
    }

    /**
     * @return if the current instance of Minecraft is on a server.
     */
    public static boolean isOnServer() {
        Minecraft minecraft = getMinecraft();
        return !minecraft.isLocalServer() && !(minecraft.getCurrentServer() == null);
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

    /**
     * @return if the current level is null.
     */
    public static boolean isCurrentLevelNull() {
        return Minecraft.getInstance().level == null;
    }
}