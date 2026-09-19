package net.dillon.quesoexample;

import net.dillon.quesoexample.entity.QuesoEntityTypes;
import net.dillon.quesoexample.item.QuesoTestItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuesoExampleMod implements ModInitializer {
    public static final String MOD_ID = "quesoexample";
    public static final Logger LOGGER = LoggerFactory.getLogger("QuesoExampleMod");

    @Override
    public void onInitialize() {
        QuesoEntityTypes.i_();
        QuesoTestItems.i_();

        LOGGER.info("Successfully initialized QuesoExample mod for DillonLib.");
    }
}