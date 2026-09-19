package net.dillon.dillonlib.mixinplugin;

/**
 * A list of mapped {@code mixin class names} with {@code booleans} on whether the mixin should be enabled upon load time.
 * <p>If the mapped boolean returns {@code true}, the every mapped mixin class id should be {@code disabled.}</p>
 * @since 1.0
 */
public class PredicateEntry {
    private final String[] mixins;
    private final boolean condition;
    private final String reason;
    private final MessageType messageType;

    /**
     * @param mixins the list of mixins that should be disabled if {@code condition} returns true
     * @param condition the condition for if the list of mixins should be disabled
     * @param reason the message that is sent to console to inform the user why the list of mixins were disabled (this cannot be blank, or else will result in a {@link IllegalStateException}).
     * @param messageType the type of message that is sent to console (info, warning, debug or error). In a developing environment, a warning message is sent for every predicate entry.
     */
    private PredicateEntry(String[] mixins, boolean condition, String reason, MessageType messageType) {
        this.mixins = mixins;
        this.condition = condition;
        this.reason = reason;
        this.messageType = messageType;
    }

    /**
     * Creates a new {@code PredicateEntry}.
     */
    public static PredicateEntry of(String[] mixins, boolean condition, String reason, MessageType messageType) {
        return new PredicateEntry(mixins, condition, reason, messageType);
    }

    /**
     * Creates a new {@code PredicateEntry}, with a {@code info} message type.
     */
    public static PredicateEntry ofInfo(String[] mixins, boolean condition, String reason) {
        return of(mixins, condition, reason, MessageType.INFO);
    }

    /**
     * Creates a new {@code PredicateEntry}, with a {@code warn} message type.
     */
    public static PredicateEntry ofWarn(String[] mixins, boolean condition, String reason) {
        return of(mixins, condition, reason, MessageType.WARN);
    }

    /**
     * Creates a new {@code PredicateEntry}, with a {@code debug} message type.
     */
    public static PredicateEntry ofDebug(String[] mixins, boolean condition, String reason) {
         return of(mixins, condition, reason, MessageType.DEBUG);
    }

    /**
     * Creates a new {@code PredicateEntry}, with a {@code error} message type.
     */
    public static PredicateEntry ofError(String[] mixins, boolean condition, String reason) {
        return of(mixins, condition, reason, MessageType.ERROR);
    }

    /**
     * @return a string array with a single mixin.
     */
    public static String[] single(String mixin) {
        return new String[]{mixin};
    }

    /**
     * @return a string array with only two mixins.
     */
    public static String[] ddouble(String m1, String m2) {
        return new String[]{m1, m2};
    }

    /**
     * @return a string array with exactly three mixins.
     */
    public static String[] triple(String m1, String m2, String m3) {
        return new String[]{m1, m2, m3};
    }

    /**
     * @return a string array with multiple mixins.
     */
    public static String[] multiple(String... mixins) {
        return mixins;
    }

    /**
     * @return the mixins to check.
     */
    public String[] mixins() {
        return this.mixins;
    }

    /**
     * @return the condition for if the mixin(s) should be applied.
     */
    public boolean condition() {
        return this.condition;
    }

    /**
     * @return the reason the mixin isn't being applied.
     */
    public String reason() {
        return this.reason;
    }

    /**
     * @return the type of message to send.
     */
    public MessageType messageType() {
        return this.messageType;
    }
}