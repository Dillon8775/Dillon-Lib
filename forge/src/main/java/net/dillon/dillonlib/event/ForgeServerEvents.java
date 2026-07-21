package net.dillon.dillonlib.event;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibMain;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Dill(DillType.DEDICATED_SERVER)
@Mod.EventBusSubscriber(modid = DillonLibMain.MOD_ID, value = Dist.DEDICATED_SERVER)
public class ForgeServerEvents {
}