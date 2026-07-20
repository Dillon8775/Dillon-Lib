package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibEvents;
import net.dillon.dillonlib.factory.ClientFactories;
import net.dillon.dillonlib.factory.Factories;
import net.dillon.dillonlib.factory.data.BoatData;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

/**
 * {@code Client-side Fabric events} for DillonLib.
 */
@Dill(DillType.CLIENT)
public class FabricClientEvents {

    public static void registerFabricClientCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            DillonLibEvents.registerAllClientCommands(commandDispatcher, commandRegistryAccess);
        });
    }

    public static void registerFabricBoatRenderers() {
        for (BoatData boat : Factories.BOATS) {
            String namespace = boat.id().getNamespace();
            String id = boat.id().getPath();

            if (boat.chest()) {
                registerBoatRenderer(
                        boat.entityType(),
                        ClientFactories.chestBoatModelLayer(namespace, id), true);
            } else {
                registerBoatRenderer(
                        boat.entityType(),
                        ClientFactories.boatModelLayer(namespace, id), false);
            }
        }
    }

    public static void registerBoatRenderer(EntityType<? extends AbstractBoat> type,
                                     ModelLayerLocation modelLayerLocation,
                                     boolean chestBoat
    ) {
        EntityRenderers.register(type, context -> new BoatRenderer(context, modelLayerLocation));
        EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, chestBoat ? BoatModel::createChestBoatModel : BoatModel::createBoatModel);
    }
}