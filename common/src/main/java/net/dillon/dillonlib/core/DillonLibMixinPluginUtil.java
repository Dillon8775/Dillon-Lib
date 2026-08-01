package net.dillon.dillonlib.core;

import net.dillon.dillonlib.mixinplugin.MessageType;
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
                        new String[]{"client.fix.bow.AbstractClientPlayerFix"},
                        !DillonLibOptions.getLibInstance().applyAbstractClientPlayerFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_abstract_client_player_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{"client.fix.bow.FabricPlayerRendererFix"},
                        !DillonLibOptions.getLibInstance().applyAvaterRendererFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_avatar_renderer_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "client.fix.bow.ItemInHandRendererFix",
                                "client.fix.bow.FabricItemInHandRendererFix"
                        },
                        !DillonLibOptions.getLibInstance().applyItemInHandRendererFix || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_item_in_hand_renderer_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "ignitable.CandleCakeBlockFix",
                                "ignitable.TntBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyIgnitableFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_ignitable_factories\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "shear.BeehiveBlockFix",
                                "shear.MatchToolFix",
                                "shear.FabricMushroomCowFix",
                                "shear.FabricPumpkinBlockFix",
                                "shear.FabricSheepFix",
                                "shear.FabricSnowGolemFix",
                                "shear.FabricTripWireBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyShearFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_shear_factories\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{"entity.PlayerMixin"},
                        !DillonLibOptions.getLibInstance().applyShieldFactories || !Platforms.shouldApplyFactories(),
                        ofFactory("\"apply_shield_factories\" is disabled."),
                        MessageType.DEBUG
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