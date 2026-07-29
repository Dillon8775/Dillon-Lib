package net.dillon.dillonlib.core;

import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.dillonlib.platform.Platforms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DillonLibMixinPluginUtil extends MixinPluginUtil {

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Mixin");
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.dillonlib.mixin.";
    }

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{
                                "client.FabricOptionInstanceMixin",
                                "client.NeoForgeOptionInstanceMixin",
                        },
                        !DillonLibOptions.getLibInstance().applyFullBrightIfRequired || !Platforms.shouldApplyFullBright(),
                        "FullBright is not enabled."
                ),
                new PredicateEntry(
                        new String[]{"client.FabricSodiumConfigBuilderMixin"},
                        !Platforms.shouldApplyFullBright() || !Platforms.getDillonLibMixinPlatform().isModLoaded(DillonLibModReferences.SODIUM),
                        "FullBright is not enabled, or Sodium is not loaded."
                ),
                new PredicateEntry(
                        new String[]{"client.fix.bow.AbstractClientPlayerFix"},
                        !DillonLibOptions.getLibInstance().applyAbstractClientPlayerFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_abstract_client_player_fix\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{"client.fix.bow.AvatarRendererFix"},
                        !DillonLibOptions.getLibInstance().applyAvaterRendererFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_avatar_renderer_fix\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{
                                "client.fix.bow.ItemInHandRendererFix",
                                "client.fix.bow.FabricItemInHandRendererFix"
                        },
                        !DillonLibOptions.getLibInstance().applyItemInHandRendererFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_item_in_hand_renderer_fix\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{
                                "ignitable.CandleCakeBlockFix",
                                "ignitable.TntBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyIgnitableFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_ignitable_factories\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{
                                "shear.BeehiveBlockFix",
                                "shear.CopperGolemFix",
                                "shear.MatchToolFix",
                                "shear.MushroomCowFix",
                                "shear.FabricBoggedFix",
                                "shear.FabricLeashFenceKnotEntityFix",
                                "shear.FabricPumpkinBlockFix",
                                "shear.FabricSheepFix",
                                "shear.FabricSnowGolemFix",
                                "shear.FabricTripWireBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyShearFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_shear_factories\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{"entity.fix.SulfurCubeFix"},
                        (!DillonLibOptions.getLibInstance().applyShearFactories && !DillonLibOptions.getLibInstance().applyIgnitableFactories) || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_shear_factories\" and \"apply_ignitable_factories\" is disabled.")
                ),
                new PredicateEntry(
                        new String[]{
                                "client.ClientPacketListenerMixin",
                                "entity.LivingEntityMixin"
                        },
                        !DillonLibOptions.getLibInstance().applyTotemFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_totem_factories\" is disabled.")
                )
        );
    }

    /**
     * @return a string of if factories are disabled, or something else.
     */
    private static String ofFactory(String s) {
        return "Factories are disabled, or " + s;
    }
}