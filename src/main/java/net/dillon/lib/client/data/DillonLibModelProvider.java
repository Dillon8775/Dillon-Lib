package net.dillon.lib.client.data;

import net.dillon.lib.annotation.GlobalUse;
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
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

/**
 * You may have to call this mod in your mod's data generator entrypoint to load all custom factory models.
 */
@GlobalUse
public class DillonLibModelProvider extends FabricModelProvider {

    public DillonLibModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (ShieldData data : DillonsRegistry.getShieldFactories()) {
            String id = data.identifier().getPath();
            SpecialModelRenderer.Unbaked specialModeRenderer = RendererRegistry.createUnbakedShieldRenderer(
                    new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base")),
                    new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/" + id + "_base_no_pattern")));
            itemModelGenerator.registerCondition(data.shield(), ItemModels.usingItemProperty(),
                    ItemModels.special(ModelIds.getItemSubModelId(data.shield(), "_blocking"), specialModeRenderer),
                    ItemModels.special(ModelIds.getItemModelId(data.shield()), specialModeRenderer));
        }
    }
}