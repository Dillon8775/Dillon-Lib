package net.dillon.quesoinstance.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.quesoinstance.QuesoInstance;
import net.dillon.quesoinstance.command.QuesoClientCommand;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

public class ClientQuesoInstanceImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return QuesoInstance.MOD_ID;
    }

    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return new KeyMapping(name, value, category);
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return false;
    }


    @Override
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoClientCommand.clientTestCommand());
    }
}