package net.dillon.quesoexample.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoClientCommand;
import net.dillon.quesoexample.helper.ModHelper;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;

public class ClientQuesoExampleImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }

    @Override
    public List<PlatformMenuButton> menuButtons() {
        return List.of(
                PlatformMenuButton.builder(ModHelper.menuButton("Quality", "qoq", true, true))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("Simple", "simplekeybinds", false, true))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("C", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("D", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("E", "qoq", false, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build(),
                PlatformMenuButton.builder(ModHelper.menuButton("F", "qoq", true, false))
                        .consumer(spriteIconButton -> System.out.println(spriteIconButton.getX()))
                        .build()
        );
    }

    @Override
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.clientTestCommand());
    }
}