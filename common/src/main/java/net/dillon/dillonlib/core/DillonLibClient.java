package net.dillon.dillonlib.core;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * {@code Client} initialization entrypoint for DillonLib.
 */
@Dill(DillType.CLIENT)
public class DillonLibClient {

    /**
     * Initializes the {@code client-side} of DillonLib.
     */
    public static void cInitialize() {
        DillonLibClient.clientInitializers().forEach(Runnable::run);

        int clientPlatforms = PlatformLoader.executeForEachClientPlatform(clientModPlatform -> {
            if (!clientModPlatform.modId().equals(DillonLibMain.MOD_ID)) {
                DillonLibMain.LOGGER.info("ClientModPlatform loaded with mod ID {}",
                        clientModPlatform.modId()
                );
            }
        });

        DillonLibMain.LOGGER.info("Loaded {} client platforms", clientPlatforms);
        DillonLibMain.LOGGER.info("(Client) DillonLib has loaded");
    }

    /**
     * @return all client-side initializer methods for {@code client-side only classes.}
     */
    private static List<Runnable> clientInitializers() {
        return List.of(
                ClientFactories::i_
        );
    }

    /**
     * @return the full bright setting component string.
     */
    public static Component toComponentString(Double gamma) {
        long brightness = Math.round(gamma * 100);
        return Component.translatable("options.gamma").append(": ").append(brightness == 0 ? Component.translatable("options.gamma.min") : brightness == 100 ? Component.translatable("options.gamma.max") : Component.literal(String.valueOf(brightness)));
    }

    /**
     * Updates full bright.
     */
    public static void onValueUpdate(Double brightness) {
        Minecraft.getInstance().options.gamma().set(brightness);
    }
}