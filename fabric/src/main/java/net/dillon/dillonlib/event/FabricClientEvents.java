package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * {@code Client-side Fabric events} for DillonLib.
 */
@Dill(DillType.CLIENT)
public class FabricClientEvents {

    public static void registerFabricClientCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            PlatformGetter.get().registerClientCommands(commandDispatcher, commandRegistryAccess);
        });
    }
}