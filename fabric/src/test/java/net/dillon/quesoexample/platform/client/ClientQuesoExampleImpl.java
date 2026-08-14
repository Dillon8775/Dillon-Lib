package net.dillon.quesoexample.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.KeybindScrollHelper;
import net.dillon.dillonlib.util.Texts;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoClientCommand;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Map;

public class ClientQuesoExampleImpl extends ClientModPlatform {
    public static final KeyMapping.Category lol = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("qualityofqueso", "quality_of_queso"));

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return KeyMappingHelper.registerKeyMapping(new KeyMapping(name, value, category));
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }

    @Override
    public List<PlatformMenuButton> menuButtons() {
        return List.of(
                new PlatformMenuButton(true, true, true, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, true, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true, false, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true, false, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true, true, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, false, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, false, menuButton(), spriteIconButton -> System.out.println(spriteIconButton.getX()))
        );
    }

    @Override
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.clientTestCommand());
    }

    public static SpriteIconButton menuButton() {
        return ClientTasks.createMenuButton(
                Identifier.withDefaultNamespace(""),
                (button) -> {
                    KeybindScrollHelper.request(lol);
                    Minecraft.getInstance().gui.setScreen(new KeyBindsScreen(null, Minecraft.getInstance().options));
                },
                Map.of(
                        false,
                        Texts.BLANK
                ),
                Texts.BLANK,
                true);
    }
}