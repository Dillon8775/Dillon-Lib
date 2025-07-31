package net.dillon.lib.client.data;

import net.dillon.lib.DillonLib;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DillonLibDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        DillonLib.debug("Initializing Dillon Lib data generator...");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DillonLibModelProvider::new);

        DillonLib.debug("Finished running through data generator.");
    }
}