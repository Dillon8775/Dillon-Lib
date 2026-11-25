package net.dillon.quesoexample.platform.client;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.client.command.ClientCommandFeedback;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoClientCommand;
import net.dillon.quesoexample.debug.*;
import net.dillon.quesoexample.helper.ModHelper;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;

import java.util.List;
import java.util.function.Function;

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
    public void registerDebugEntries(WritableRegistry<DebugScreenEntry> registry) {
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_ONE, new TestDebugEntry1());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_TWO, new TestDebugEntry2());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_THREE, new TestDebugEntry3());
        Registry.register(registry, TestDebugKeysAndGroups.TEST_ENTRY_FOUR, new TestDebugEntry4());
    }

    @Override
    public <S> void registerClientCommands(CommandDispatcher<S> dispatcher, Function<S, ClientCommandFeedback> feedback, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.create(feedback));
    }
}