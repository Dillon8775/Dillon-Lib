package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.factory.data.BoatData;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

/**
 * {@code Client-side NeoForge events} for DillonLib.
 */
@Dill(DillType.CLIENT)
@EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping keyMapping : ClientFactories.NON_KUMA_KEY_MAPPING_FACTORIES.keySet()) {
            event.register(keyMapping);
        }
    }

    @SubscribeEvent
    public static void registerBoatRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (BoatData boat : Factories.BOATS) {
            String namespace = boat.id().getNamespace();
            String id = boat.id().getPath();

            if (boat.chest()) {
                event.registerEntityRenderer(
                        boat.entityType(),
                        context -> new BoatRenderer(context, ClientFactories.chestBoatModelLayer(namespace, id)));
            } else {
                event.registerEntityRenderer(
                        boat.entityType(),
                        context -> new BoatRenderer(context, ClientFactories.boatModelLayer(namespace, id)));
            }
        }
    }

    @SubscribeEvent
    public static void registerBoatLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (BoatData boat : Factories.BOATS) {
            String namespace = boat.id().getNamespace();
            String id = boat.id().getPath();

            if (boat.chest()) {
                event.registerLayerDefinition(
                        ClientFactories.chestBoatModelLayer(namespace, id),
                        BoatModel::createChestBoatModel
                );
            } else {
                event.registerLayerDefinition(
                        ClientFactories.boatModelLayer(namespace, id),
                        BoatModel::createBoatModel
                );
            }
        }
    }
}