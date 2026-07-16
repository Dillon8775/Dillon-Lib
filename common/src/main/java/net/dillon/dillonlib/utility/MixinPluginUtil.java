package net.dillon.dillonlib.utility;

import net.dillon.dillonlib.platform.ModReference;
import org.slf4j.Logger;

import java.util.List;

/**
 * Utility class for Conditional mixin plugin.
 */
@SuppressWarnings("unchecked")
public abstract class MixinPluginUtil {

    /**
     * @return the logger for a mixin plugin. It should only be used within the conditional mixin plugin.
     */
    public abstract Logger logger();

    /**
     * @return the list of predicates, mixin class names and message reasons for mixins that should be disabled.
     */
    public abstract List<PredicateEntry> entries();

    /**
     * @return if a mod is loaded on a specific platform.
     * <p>This is the method that should be used for conditional mixin plugins, not the one in the {@code ModPlatform} class.</p>
     */
    public abstract boolean isModLoaded(ModReference mod);

    /**
     * @return {@code false} if mixin should not apply.
     */
    public boolean shouldNotApply(String targetClassName, String mixinClassName) {
        for (PredicateEntry entry : entries()) {
            if (entry.condition()) {
                for (String s : entry.mixins()) {
                    String name = "net.dillon.qualityofqueso.mixin." + s;
                    if (name.equals(mixinClassName)) {
                        logger().warn("Skipping mixin {} for class {}: {}",
                                mixinClassName,
                                targetClassName,
                                entry.reason()
                        );
                        return true;
                    }
                }
            }
        }

        return false;
    }
}