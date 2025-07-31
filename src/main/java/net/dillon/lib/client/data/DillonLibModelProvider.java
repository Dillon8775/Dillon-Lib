package net.dillon.lib.client.data;

import net.dillon.lib.client.render.RendererRegistry;
import net.dillon.lib.registry.DillonsRegistry;
import net.dillon.lib.registry.data.ShieldData;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.data.ModelIds;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class DillonLibModelProvider extends FabricModelProvider {

    public DillonLibModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        System.out.println(DillonsRegistry.getShieldFactories().size());
        for (ShieldData data : DillonsRegistry.getShieldFactories()) {
            String id = data.identifier().getPath();
            SpriteIdentifier base = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base"));
            SpriteIdentifier baseNoPattern = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base_no_pattern"));
            SpecialModelRenderer.Unbaked specialModeRenderer =
                    RendererRegistry.createShieldRenderer(base, baseNoPattern);
            ItemModel.Unbaked unbaked = ItemModels.special(ModelIds.getItemModelId(data.shield()), specialModeRenderer);
            ItemModel.Unbaked unbaked2 = ItemModels.special(ModelIds.getItemSubModelId(data.shield(), "_blocking"), specialModeRenderer);
            itemModelGenerator.registerCondition(data.shield(), ItemModels.usingItemProperty(), unbaked2, unbaked);
        }
    }
}