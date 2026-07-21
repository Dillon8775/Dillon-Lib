package net.dillon.dillonlib.mixinplugin;

/**
 * Specifies the type of message that should be sent when a mixin is not applied.
 * @since 1.0
 * @see PredicateEntry
 * @see MixinPluginUtil
 */
public enum MessageType {
    INFO,
    WARN,
    DEBUG,
    ERROR;
}