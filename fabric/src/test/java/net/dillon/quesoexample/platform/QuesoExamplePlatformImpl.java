package net.dillon.quesoexample.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.info.Platform;
import net.dillon.dillonlib.platform.info.Release;
import net.dillon.quesoexample.QuesoExampleMod;
import net.dillon.quesoexample.command.QuesoCommand;
import net.dillon.quesoexample.command.QuesoServerCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class QuesoExamplePlatformImpl extends ModPlatform {

    @Override
    public String modId() {
        return QuesoExampleMod.MOD_ID;
    }

    @Override
    public Release release() {
        return Release.ALPHA;
    }

    @Override
    public Platform platform() {
        return Platform.FABRIC;
    }

    @Override
    public String modVersion() {
        return "1.7.16";
    }

    @Override
    public void registerEvents() {
        Factories.registerSimpleItemGroupFactory(Identifier.fromNamespaceAndPath("quesoexample", "yay"), Items.GOLD_INGOT, () -> {
            ItemStack stack = new ItemStack(Items.ANCIENT_DEBRIS);
            stack.set(DataComponents.CUSTOM_NAME, Component.literal("lol"));
            return List.of(stack);
        });
        Factories.registerSimpleItemGroupFactory(Identifier.fromNamespaceAndPath("quesoexample", "yay2"), Items.GOLD_BLOCK, () -> List.of(Items.OAK_FENCE.getDefaultInstance()));
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