package net.dillon.quesoexample.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoCommand;
import net.dillon.quesoexample.command.QuesoServerCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public void registerEvents() {
        Factories.registerSimpleItemGroupFactory(new ResourceLocation("quesoexample", "yay"), Items.GOLD_INGOT, () -> {
            ItemStack stack = new ItemStack(Items.ANCIENT_DEBRIS);
            return List.of(stack);
        });
        Factories.registerSimpleItemGroupFactory(new ResourceLocation("quesoexample", "yay2"), Items.GOLD_BLOCK, () -> List.of(Items.OAK_FENCE.getDefaultInstance()));
        Factories.registerSimpleItemGroupFactory(new ResourceLocation("quesoexample", "yay3"), Items.DIAMOND_BLOCK, () -> List.of(Items.OAK_FENCE.getDefaultInstance()));
        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.BUILDING_BLOCKS, List.of(Items.FLINT_AND_STEEL, Items.DIAMOND));
    }

    @Override
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoCommand.testCommand());
    }

    @Override
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        dispatcher.register(QuesoServerCommand.serverTestCommand());
    }
}