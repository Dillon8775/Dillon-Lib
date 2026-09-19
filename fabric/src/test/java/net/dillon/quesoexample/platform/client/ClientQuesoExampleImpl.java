package net.dillon.quesoexample.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
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
                new PlatformMenuButton(true, false, ModHelper.menuButton("A", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("a", false), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true,  ModHelper.menuButton("B", false), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true, ModHelper.menuButton("C", false), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, true, ModHelper.menuButton("D", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("E", false), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX())),
                new PlatformMenuButton(true, false, ModHelper.menuButton("F", true), spriteIconButton -> System.out.println(spriteIconButton.getX()))
        );
    }

    @Override
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.clientTestCommand());
    }
}