package net.dillon.dillonlib.task;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibModReferences;
import net.dillon.dillonlib.mixin.accessor.DebugOptionsScreenAccessor;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.util.CommonSprites;
import net.dillon.dillonlib.util.DebugOptionsScreenImpl;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.debug.DebugOptionsScreen;
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

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Client-side only methods and tasks that can be commonly used throughout your mod.
 * @since 1.0
 */
@Dill(DillType.CLIENT)
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
     * Blits a texture.
     */
    public static void blitTexture(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int width, int height) {
        blitTexture(graphics, sprite, x, y, width, height, 1.0F);
    }

    /**
     * Blits a small texture over a button.
     */
    public static void blitSmallTexture(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        blitSmallTexture(graphics, sprite, button, 1.0F);
    }

    /**
     * Blits a large texture over a button.
     */
    public static void blitLargeTexture(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        blitLargeTexture(graphics, sprite, button, 1.0F);
    }

    /**
     * Blits a large texture over a button with a custom alpha.
     */
    public static void blitLargeTexture(GuiGraphicsExtractor graphics, Identifier sprite, Button button, float f) {
        blitTexture(graphics, sprite, button.getX() + 1, button.getY() + 1, 18, 18, f);
    }

    /**
     * Blits a small texture over a button with a custom alpha.
     */
    public static void blitSmallTexture(GuiGraphicsExtractor graphics, Identifier sprite, Button button, float f) {
        blitTexture(graphics, sprite, button.getX() + 2, button.getY() + 2, 16, 16, f);
    }

    /**
     * Blits a texture with a custom alpha value.
     */
    public static void blitTexture(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int width, int height, float f) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, sprite, x, y, 0.0F, 0.0F, width, height, width, height, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Draws a small sprite over a button.
     */
    public static void drawSmallSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        drawSmallSprite(graphics, sprite, button, 1.0F);
    }

    /**
     * Draws a large sprite over a button.
     */
    public static void drawLargeSprite(GuiGraphicsExtractor graphics, Identifier sprite, Button button) {
        drawLargeSprite(graphics, sprite, button, 1.0F);
    }

    /**
     * Draws a large sprite over a button with a custom alpha.
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
     * Draws a sprite.
     */
    public static void drawSprite(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int width, int height) {
        drawSprite(graphics, sprite, x, y, width, height, 1.0F);
    }

    /**
     * Draws a sprite with a custom alpha fade.
     */
    public static void drawSprite(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int width, int height, float f) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x, y, width, height, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Draws the update icon.
     */
    public static void drawUpdateSprite(GuiGraphicsExtractor graphics, int x, int y) {
        drawSprite(graphics, CommonSprites.UPDATE_AVAILABLE, x, y, 8, 8);
    }

    /**
     * Draws a mod version and icon in a main menu screen.
     */
    public static void drawModInfo(GuiGraphicsExtractor graphics, Screen menuScreen, Component version, int widthModifier, Identifier logo, boolean hasUpdate) {
        int textWidth = menuScreen.width - 20;
        int textHeight = menuScreen.height - 21;
        int imageWidth = menuScreen.width - widthModifier;
        int imageHeight = menuScreen.height - 26;

        graphics.centeredText(menuScreen.getFont(), version, textWidth, textHeight, CommonColors.WHITE);
        drawSprite(graphics, logo, imageWidth, imageHeight, 18, 18);

        if (hasUpdate) {
            drawUpdateSprite(graphics, imageWidth - 2, imageHeight - 2);
        }
    }

    /**
     * Tries to open a {@code YetAnotherConfigLib} screen, if the mod is installed, and warns the user if it's not.
     */
    public static void tryOpenYaclScreen(Supplier<Screen> configScreen, Component modName) {
        Gui gui = Minecraft.getInstance().gui;

        if (!DillonLibModReferences.isModLoaded(DillonLibModReferences.YACL)) {
            gui.toastManager().addToast(new SystemToast(
                    SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                    Component.translatable("dillonlib.toast.title.yacl").withStyle(ChatFormatting.RED),
                    Component.translatable("dillonlib.toast.yacl", modName)));
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
     * Renders the update icon on a button.
     */
    public static void renderUpdateIconOnButton(GuiGraphicsExtractor graphics, UpdatableSpriteButton button) {
        if (button == null || !button.shouldRenderUpdateSprite()) {
            return;
        }

        drawUpdateSprite(graphics, button.getX() + 14, button.getY() - 3);
    }

    /**
     * Creates a menu button.
     */
    public static UpdatableSpriteButton createMenuButton(String name, Identifier sprite, Button.OnPress onPress, Map<Boolean, Component> update, Component tooltip, boolean withTooltip) {
        return createMenuButton(name, sprite, onPress, update, tooltip, 16, 16, withTooltip);
    }

    /**
     * Creates a menu button with a custom sprite width and height.
     */
    public static UpdatableSpriteButton createMenuButton(String name, Identifier sprite, Button.OnPress onPress, Map<Boolean, Component> update, Component tooltip, int spriteWidth, int spriteHeight, boolean withTooltip) {
        Component text = tooltip;
        boolean hasUpdate = update.containsKey(true);
        if (hasUpdate) {
            text = update.getOrDefault(true, Component.empty());
        }
        if (!withTooltip) {
            text = Component.empty();
        }

        return createSpriteIconButton(name, sprite, onPress, text, spriteWidth, spriteHeight, hasUpdate);
    }

    /**
     * Creates a {@link UpdatableSpriteButton} with a custom sprite width and height.
     */
    public static UpdatableSpriteButton createSpriteIconButton(String name, Identifier sprite, Button.OnPress onPress, Component tooltip, int spriteWidth, int spriteHeight, boolean hasUpdate) {
        UpdatableSpriteButton button = new UpdatableSpriteButton(name, new WidgetSprites(sprite), onPress, hasUpdate, spriteWidth, spriteHeight);
        if (!tooltip.equals(Component.empty())) {
            button.setTooltip(Tooltip.create(tooltip));
        }
        return button;
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