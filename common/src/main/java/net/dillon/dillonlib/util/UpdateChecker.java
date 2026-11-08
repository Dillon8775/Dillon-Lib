package net.dillon.dillonlib.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dillon.dillonlib.core.DillonLibOptions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A utility to see if your mod needs an update.
 */
public class UpdateChecker {
    private static final HttpClient HTTP_CLIENT =
            HttpClient.newHttpClient();

    /**
     * Checks Modrinth for an update.
     * @param modrinthSlug the Modrinth project ID or slug
     * @param currentVersion the installed mod version
     * @return a future containing true if an update is available
     */
    public static CompletableFuture<Boolean> checkForUpdate(String modrinthSlug, String currentVersion) {
        String url = "https://api.modrinth.com/v2/project/" + modrinthSlug + "/version";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "DillonLib Update Checker")
                .GET()
                .build();

        return HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() != 200) {
                        return false;
                    }

                    return hasUpdate(
                            response.body(),
                            currentVersion
                    );
                })
                .exceptionally(error -> {
                    error.printStackTrace();
                    return false;
                });
    }

    /**
     * @return if a mod has an update.
     */
    public static boolean hasUpdate(CompletableFuture<Boolean> completableFuture) {
        if (!DillonLibOptions.getLibInstance().core().checkForUpdates) {
            return false;
        }

        try {
            if (completableFuture.get()) {
                return true;
            }
        } catch (InterruptedException | ExecutionException o) {
            return false;
        }

        return false;
    }

    /**
     * Checks whether the latest version is newer.
     */
    public static boolean compare(String currentVersion, String latestVersion) {
        return compareVersions(latestVersion, currentVersion) > 0;
    }

    /**
     * @return if a mod has an update, based on the current version.
     */
    private static boolean hasUpdate(String response, String currentVersion) {
        JsonArray versions = JsonParser.parseString(response).getAsJsonArray();

        if (versions.isEmpty()) {
            return false;
        }

        JsonObject latestVersion = versions.get(0).getAsJsonObject();

        String latestVersionNumber = latestVersion.get("version_number").getAsString();

        return compare(currentVersion, latestVersionNumber);
    }

    /**
     * Compares two mod versions, ignoring Minecraft versions and mod loaders.
     */
    public static int compareVersions(String version1, String version2) {
        version1 = version1.contains("-")
                ? version1.split("-")[1]
                : version1;

        version2 = version2.contains("-")
                ? version2.split("-")[1]
                : version2;

        String[] version1Parts = version1.split("\\.");
        String[] version2Parts = version2.split("\\.");

        int length = Math.max(version1Parts.length, version2Parts.length);

        for (int i = 0; i < length; i++) {
            int part1 = i < version1Parts.length
                    ? Integer.parseInt(
                    version1Parts[i])
                    : 0;

            int part2 = i < version2Parts.length
                    ? Integer.parseInt(
                    version2Parts[i])
                    : 0;

            if (part1 > part2) {
                return 1;
            }

            if (part1 < part2) {
                return -1;
            }
        }

        return 0;
    }
}