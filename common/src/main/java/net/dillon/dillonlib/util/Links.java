package net.dillon.dillonlib.util;

/**
 * Common links that are used in Dillon's mods.
 * @since 1.2
 */
public class Links {
    public static final String DILLONS_DISCORD = "https://discord.gg/vfqEAn4YFy";

    /**
     * @return a link to your {@code GitHub repository}.
     */
    public static String github(String slug) {
        return "https://github.com/" + slug;
    }

    /**
     * @return a link to your {@code GitHub issues} page.
     */
    public static String githubIssues(String slug) {
        return github(slug) + "/issues";
    }
}