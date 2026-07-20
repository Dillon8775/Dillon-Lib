package net.dillon.quesoinstance.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.dillon.quesoinstance.QuesoInstance;
import net.dillon.quesoinstance.command.QuesoClientCommand;
import net.dillon.quesoinstance.command.QuesoCommand;
import net.dillon.quesoinstance.command.QuesoServerCommand;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuesoInstancePlatformImpl extends ModPlatform {

    @Override
    public String modId() {
        return QuesoInstance.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return LoggerFactory.getLogger("QuesoInstance/Test");
    }

    @Override
    public String modVersion() {
        return "6.7";
    }

    @Override
    public @NotNull PlatformName platformName() {
        return PlatformName.NEOFORGE;
    }

    @Override
    public @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.ALPHA;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.DEFAULT;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return false;
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