package net.dillon.quesoexample.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoClientCommand;
import net.dillon.quesoexample.command.QuesoCommand;
import net.dillon.quesoexample.command.QuesoServerCommand;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuesoExamplePlatformImpl extends ModPlatform {

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return LoggerFactory.getLogger("QuesoTest/Main");
    }

    @Override
    public String modVersion() {
        return "6.7";
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.FABRIC;
    }

    @Override
    public @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.ALPHA;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.LONG_PATCH;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return true;
    }

    @Override
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoCommand.testCommand());
    }

    @Override
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.clientTestCommand());
    }

    @Override
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoServerCommand.serverTestCommand());
    }
}