package net.dillonlib.client.render;

import net.dillonlib.annotation.PrivateUse;
import net.dillonlib.registry.DLR;
import net.dillonlib.registry.data.BoatData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.util.Identifier;

/**
 * Registers all custom renderers.
 */
@PrivateUse
@Environment(EnvType.CLIENT)
public class RendererRegistry {

    /**
     * Registers all {@link BlockRenderLayerMap} blocks.
     */
    public static void registerCutoutBlocks() {
        for (Block block : DLR.getCutoutBlocks()) {
            BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT);
        }
    }

    /**
     * Registers a {@code boat renderer} for a {@code chest boat} and a {@code normal boat.}
     */
    public static void registerBoatRenderers() {
        for (BoatData boatData : DLR.getBoatFactories()) {
            String namespace = boatData.identifier().getNamespace();
            String id = boatData.identifier().getPath();

            if (boatData.chest()) {
                registerBoatRenderer(boatData.entityType(),
                        chestBoatModelLayer(namespace, id), true);
            }
            registerBoatRenderer(boatData.entityType(),
                    boatModelLayer(namespace, id), false);
        }
    }

//    /**
//     * @return a {@code shield factory renderer} based on a registered shield factory.
//     */
//    public static UnbakedShieldFactory createUnbakedShieldRenderer(SpriteIdentifier base, SpriteIdentifier baseNoPattern) {
//        return new UnbakedShieldFactory(base, baseNoPattern);
//    }

    /**
     * Registers a boat renderer.
     */
    private static <T extends AbstractBoatEntity> void registerBoatRenderer(EntityType<? extends T> entityType, EntityModelLayer modelLayer, boolean chest) {
        registerEntityRenderer(
                entityType,
                modelLayer,
                chest ? BoatEntityModel::getChestTexturedModelData : BoatEntityModel::getTexturedModelData,
                context -> new BoatEntityRenderer(context, modelLayer)
        );
    }

    /**
     * Registers entity renderers for the speedrunner mod boats.
     */
    private static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityModelLayer modelLayer, EntityModelLayerRegistry.TexturedModelDataProvider texturedModelDataProvider, EntityRendererFactory<T> entityRendererFactory) {
        EntityModelLayerRegistry.registerModelLayer(modelLayer, texturedModelDataProvider);
        EntityRendererRegistry.register(entityType, entityRendererFactory);
    }

    /**
     * @return the texture path for a {@code normal boat.}
     */
    private static EntityModelLayer boatModelLayer(String namespace, String id) {
        return new EntityModelLayer(Identifier.of(namespace, "boat/" + id), "main");
    }

    /**
     * @return the texture path for a {@code chest boat.}
     */
    private static EntityModelLayer chestBoatModelLayer(String namespace, String id) {
        return new EntityModelLayer(Identifier.of(namespace, "chest_boat/" + id), "main");
    }
}