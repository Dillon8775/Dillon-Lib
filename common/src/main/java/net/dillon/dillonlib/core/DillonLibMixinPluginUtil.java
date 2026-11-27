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
                PredicateEntry.ofDebug(
                        PredicateEntry.fabricAndNeoForge(
                                "client.%sOptionInstanceMixin"
                        ),
                        !Platforms.shouldApplyFullBright() || !DillonLibOptions.getLibInstance().core().applyFullBrightIfRequired,
                        "FullBright is not enabled."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.single("client.fix.screen.DebugOptionsScreenMixin"),
                        !DillonLibOptions.getLibInstance().factories().applyDebugOptionsScreenFix,
                        "apply_debug_options_screen_fix is disabled."
                ),
                PredicateEntry.ofInfo(
                        PredicateEntry.single("client.FabricOldSodiumConfigBuilderMixin"),
                        !Platforms.shouldApplyFullBright()
                                || !DillonLibModReferences.isModLoaded(DillonLibModReferences.SODIUM)
                                || DillonLibModReferences.isVersionPresent(DillonLibModReferences.SODIUM),
                        "FullBright is not enabled, or old Sodium is not loaded."
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