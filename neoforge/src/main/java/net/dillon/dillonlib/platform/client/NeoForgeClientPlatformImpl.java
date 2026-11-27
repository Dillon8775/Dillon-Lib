package net.dillon.dillonlib.platform.client;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.client.command.ClientCommandFeedback;
import net.dillon.dillonlib.core.DillonLibClient;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraft.commands.CommandBuildContext;

import java.util.function.Function;

@Dill(env = DillEnv.CLIENT)
public class NeoForgeClientPlatformImpl extends ClientModPlatform {

    @Override
    public String modId() {
        return DillonLibMain.MOD_ID;
    }

    @Override
    public <S> void registerClientCommands(CommandDispatcher<S> dispatcher, Function<S, ClientCommandFeedback> feedback, CommandBuildContext commandRegistryAccess) {
        DillonLibClient.Events.registerReloadCommand(dispatcher, feedback);
    }
}