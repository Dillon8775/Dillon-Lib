package net.dillon.dillonlib.mixinplugin;

/**
 * A list of mapped {@code mixin class names} with {@code booleans} on whether the mixin should be enabled upon load time.
 * <p>If the mapped boolean returns {@code true}, the every mapped mixin class name should be {@code disabled.}</p>
 * @since 1.0
 * @param mixins the list of mixins that should be disabled if {@code condition} returns true
 * @param condition the condition for if the list of mixins should be disabled
 * @param reason the message that is sent to console to inform the user why the list of mixins were disabled (this cannot be blank, or else will result in a {@link IllegalStateException}).
 * @param messageType the type of message that is sent to console (info, warning, debug or error). In a developing environment, a warning message is sent for every predicate entry.
 */
public record PredicateEntry(String[] mixins, boolean condition, String reason, MessageType messageType) {}