package net.dillon.dillonlib;

import net.dillon.dillonlib.main.CommonMain;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dillonlib")
public final class DillonLibForge {

    public DillonLibForge(FMLJavaModLoadingContext context) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientDillonLibForge::init);
        DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> ServerDillonLibForge::init);

        CommonMain.initialize();
    }
}