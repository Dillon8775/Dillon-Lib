package net.dillon.dillonlib.platform.info;

/**
 * All valid platforms that are commonly use in Minecraft modding that you can use with your mod. If you are coding a mod on multiple platforms, make sure each of your platforms return the correct platform name.
 * @since 1.0
 */
public enum PlatformName {
    FABRIC,
    NEOFORGE,
    FORGE,
    OTHER;

    public boolean fabric() {
        return this == FABRIC;
    }

    public boolean neoforge() {
        return this == NEOFORGE;
    }

    public boolean forge() {
        return this == FORGE;
    }

    public boolean other() {
        return this == OTHER;
    }
}