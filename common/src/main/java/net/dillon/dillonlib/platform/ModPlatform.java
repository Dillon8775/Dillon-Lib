package net.dillon.dillonlib.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/**
 * A platform helper class. Can be used for various different calls and functions, and new methods can easily be added in the mod's specified platform.
 * <p>Each platform for your mod (ex. fabric and neoforge) should contain {@code three service loaders}, one for this class, one for {@code ClientModPlatform} (for client-side only access code), and one for the {@code MixinModPlatform}, which is a mixin-safe class for methods that can be used in conditional mixin plugins.</p>
 * @since 1.0
 * @see net.dillon.dillonlib.platform.client.ClientModPlatform
 * @see net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform
 */
public abstract class ModPlatform implements Loadable {

    /**
     * @return the mod id for this platform. Should return your mod id from your common mod class.
     */
    @Override
    public abstract String modId();

    /**
     * @return the logger for your mod.
     */
    public abstract @NotNull Logger logger();

    /**
     * @return your mod version.
     */
    public abstract String modVersion();

    /**
     * @return the {@link PlatformName} (fabric, neoforge, forge, etc.)
     */
    public abstract @NotNull PlatformName platformName();

    /**
     * @return the {@link PlatformRelease} (stable/release, beta, or alpha).
     */
    public abstract @NotNull PlatformRelease platformRelease();

    /**
     * @return the {@link LogoWidth}. Used specifically in Dillon's mods, but you can branch your own logic with this if you'd like. Otherwise this doesn't do much.
     */
    public abstract @NotNull LogoWidth logoWidth();

    /**
     * @return if a packet in your mod can be sent. It's helpful to check if a packet can be sent when joining a server that doesn't have your mod installed, which can inform the user of mod incompatibility. If you don't have a reason to check if a packet can be sent in your mod, you can just return false here.
     */
    public abstract boolean canSendPacket(LocalPlayer localPlayer);

    /**
     * Registers commands that should be on all environments. Can be blank if you have no custom commands in your mod.
     */
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    /**
     * Registers client-side only commands. Can be blank if you have no custom commands in your mod.
     */
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    /**
     * Registers server-side only commands. Can be blank if you have no custom commands in your mod.
     */
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }
}