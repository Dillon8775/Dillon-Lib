package net.dillon.dillonlib.platform;

/**
 * Represents a loadable platform. This is used to check for the correct mod id in loaded platforms, and so the platforms can be read correctly based on your mod id. You can also use this interface to create your own platform.
 * @since 1.0
 */
public interface Loadable {
    String modId();
}