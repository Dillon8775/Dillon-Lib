package net.dillon.lib.client.data;

import net.dillon.lib.DillonLib;
import net.dillon.lib.annotation.PrivateUse;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;

@PrivateUse
public class DillonLibDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        DillonLib.debug("Initializing Dillon Lib data generator...");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DillonLibModelProvider::new);

        DataGenerator.Pack secondPack = fabricDataGenerator.createPack();
        secondPack.addProvider(DillonLibAtlasProvider::new);

        DillonLib.debug("Finished running through data generator.");
    }
}