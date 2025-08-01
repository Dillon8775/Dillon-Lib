package net.dillon.lib.client.data;

import net.dillon.lib.registry.DillonsCRegistry;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.client.texture.atlas.AtlasSourceManager;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.client.data.AtlasDefinitionProvider.createSingleAtlasSource;

public class DillonLibAtlasProvider implements DataProvider {
    private final DataOutput.PathResolver pathResolver;

    public DillonLibAtlasProvider(DataOutput output) {
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "atlases");
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        return CompletableFuture.allOf(
                this.runForAtlas(writer, Identifier.of("shield_patterns"), createShieldFactorySources())
        );
    }

    private static List<AtlasSource> createShieldFactorySources() {
        List<AtlasSource> shieldPatterns = new ArrayList<>();
        for (SpriteIdentifier[] spriteIdentifiers : DillonsCRegistry.getShieldIdentifiers()) {
            for (SpriteIdentifier spriteIdentifier : spriteIdentifiers) {
                shieldPatterns.add(createSingleAtlasSource(spriteIdentifier));
            }
        }
        return shieldPatterns;
    }

    private CompletableFuture<?> runForAtlas(DataWriter writer, Identifier atlasId, List<AtlasSource> atlasSources) {
        return DataProvider.writeCodecToPath(writer, AtlasSourceManager.LIST_CODEC, atlasSources, this.pathResolver.resolveJson(atlasId));
    }

    @Override
    public String getName() {
        return "dillonlib_atlas_provider";
    }
}