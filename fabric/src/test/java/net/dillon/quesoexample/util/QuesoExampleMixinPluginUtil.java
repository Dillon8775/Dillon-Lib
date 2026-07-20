package net.dillon.quesoexample.util;

import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QuesoExampleMixinPluginUtil extends MixinPluginUtil {

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("QuesoExample/Mixin");
    }

    @Override
    public List<PredicateEntry> entries() {
        return List.of();
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.quesoexample.mixin";
    }
}