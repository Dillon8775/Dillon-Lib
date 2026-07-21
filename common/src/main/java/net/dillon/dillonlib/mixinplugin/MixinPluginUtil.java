package net.dillon.dillonlib.mixinplugin;

import net.dillon.dillonlib.platform.common.CommonPlatformGetter;
import org.slf4j.Logger;

import java.util.List;

/**
 * Simple utility class for {@link org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin}. You can use this to easily disable mixins on certain conditions with the help of {@link PredicateEntry}s
 * <p>{@code logger} - the logger to be used specifically for the mixin plugin.
 * Do not use an outside logger, as you risk Minecraft crashing due to pre-mature load time.
 * <p>{@code mixinDirectory} - the mixin directory to be checked with the plugin. You can have the method return {@code blank} or {@code ""} if you want to specifically all mixin class names by their full package name. However, it's easier to specify the package name with this method, and then simply input the mixin classes names.
 * <p>{@code entries} - the list of {@link PredicateEntry} to check for each mixin loaded at load time. If the predicate entry {@code boolean} returns true, the list of mixins in the entry will be disabled at load time.
 * @since 1.0
 * @see PredicateEntry
 * @see org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
 */
public abstract class MixinPluginUtil {

    /**
     * @return the logger for a mixin plugin. It should only be used within the conditional mixin plugin.
     */
    public abstract Logger logger();

    /**
     * @return the mixin directory used to check for mixins.
     * <p>Example: "net.dillon.dillonlib.mixin."</p>
     */
    public abstract String mixinDirectory();

    /**
     * @return the list of predicates, mixin class names and message reasons for mixins that should be disabled.
     */
    public abstract List<PredicateEntry> entries();

    /**
     * @return {@code false} if mixin should not apply. It is not recommended to override this method, as this provides the full functionality for {@link PredicateEntry}.
     */
    public boolean shouldNotApply(String targetClassName, String mixinClassName) {
        for (PredicateEntry entry : entries()) {
            if (entry.reason().isEmpty() || entry.reason().isBlank()) {
                throw new IllegalStateException("Mixin predicate entry reason cannot be blank!");
            }

            if (entry.condition()) {
                for (String s : entry.mixins()) {
                    String name = this.mixinDirectory() + s;
                    if (name.equals(mixinClassName)) {
                        message(entry, mixinClassName, targetClassName);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * The message that is sent to the console. This can be over-ridden if you wish to change the message.
     */
    public void message(PredicateEntry entry, String mixinClassName, String targetClassName) {
        sendMessage(entry, "Skipping mixin {} for class {}: {}", mixinClassName,
                targetClassName,
                entry.reason());
    }

    /**
     * Sends a console message to the user, based on the {@link MessageType}.
     */
    public void sendMessage(PredicateEntry entry, String message, Object... arguments) {
        if (CommonPlatformGetter.get().isDevelopmentEnvironment()) {
            logger().warn(message, arguments);
        } else {
            switch (entry.messageType()) {
                case WARN -> logger().warn(message, arguments);
                case DEBUG -> logger().debug(message, arguments);
                case ERROR -> logger().error(message, arguments);
                default -> logger().info(message, arguments);
            }
        }
    }
}