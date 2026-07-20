package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;

/**
 * {@code Server-side NeoForge events} for DillonLib.
 */
@Dill(DillType.DEDICATED_SERVER)
@EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.DEDICATED_SERVER)
public class NeoForgeServerEvents {
}