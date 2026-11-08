package net.dillon.dillonlib.client.screen;

import com.mojang.blaze3d.Blaze3D;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.core.DillonLibModReferences;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;
import net.dillon.dillonlib.task.ClientTasks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.dillonlib.widespread.DillonsSprites.DILLONLIB_LOGO;

/**
 * Opens main configuration for DillonLib.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.CORE)
public class DillonLibMainMenuScreen extends DillonLibMenuScreen {
    private final List<AbstractWidget> otherMods = new ArrayList<>();

    public DillonLibMainMenuScreen(Screen lastScreen) {
        super(lastScreen, Component.translatable("dillonlib.menu").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    protected void renderModInfo(GuiGraphicsExtractor graphics) {
        ClientTasks.drawModInfo(
                graphics,
                this,
                Component.literal(Platforms.getUniversalPlatform().commonModVersion(DillonLibMain.MOD_ID))
                        .withStyle(ChatFormatting.RED),
                DILLONLIB_LOGO,
                DillonLibMain.HAS_UPDATE
        );
    }

    @Override
    public void widgets() {
        list.addHeader(Component.translatable("dillonlib.header.options"));

        list.addBig(
                Button.builder(Component.translatable("dillonlib.gui.mods")
                                .withStyle(ChatFormatting.LIGHT_PURPLE),
                        b -> openScreen(new DillonsModsScreen(this))
                ).build()
        );

        list.addBig(
                Button.builder(Component.translatable("dillonlib.gui.config")
                        .withColor(CommonColors.SOFT_RED), b -> {
                    Blaze3D.openPath(
                            Platforms.getUniversalPlatform().configDir()
                                    .toFile().toPath().resolve("dillonlib.json")
                    );
                }).build()
        );

        addMod(DillonLibModReferences.QUALITY_OF_QUESO, Component.translatable("dillonlib.mod.quality_of_queso"), Component.translatable("dillonlib.mod.quality_of_queso.tooltip"), "https://modrinth.com/mod/quality-of-queso");
        addMod(DillonLibModReferences.SURVIVAL_FLY, Component.translatable("dillonlib.mod.survival_fly"), Component.translatable("dillonlib.mod.survival_fly.tooltip"), "https://modrinth.com/mod/survival-fly");
        addMod(DillonLibModReferences.SIMPLE_KEYBINDS, Component.translatable("dillonlib.mod.simple_keybinds"), Component.translatable("dillonlib.mod.simple_keybinds.tooltip"), "https://modrinth.com/mod/simple-keybinds");
        addMod(DillonLibModReferences.ENCHANTMENT_TRANSFERRING, Component.translatable("dillonlib.mod.enchantment_transferring"), Component.translatable("dillonlib.mod.enchantment_transferring.tooltip"), "https://modrinth.com/mod/enchantment-transferring");
        addMod(DillonLibModReferences.THE_NOFOG_MOD, Component.translatable("dillonlib.mod.the_nofog_mod"), Component.translatable("dillonlib.mod.the_nofog_mod.tooltip"), "https://modrinth.com/mod/the-nofog-mod");
        addMod(DillonLibModReferences.SPEEDRUNNER_MOD, Component.translatable("dillonlib.mod.speedrunner_mod"), Component.translatable("dillonlib.mod.speedrunner_mod.tooltip"), "https://modrinth.com/mod/speedrunner-mod");

        if (!otherMods.isEmpty()) {
            list.addHeader(Component.translatable("dillonlib.header.other_mods"));
            list.addSmall(otherMods);
        }
    }

    /**
     * Adds a mod to the list of other mods to download.
     */
    private void addMod(ModReference reference, Component modName, Component tooltip, String modLink) {
        if (!DillonLibModReferences.isModLoaded(reference)) {
            otherMods.add(
                    Button.builder(modName,
                            ConfirmLinkScreen.confirmLink(this, URI.create(modLink)))
                            .tooltip(
                                    Tooltip.create(tooltip)
                            )
                            .build()
            );
        }
    }
}