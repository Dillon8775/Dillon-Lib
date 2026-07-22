package net.dillon.quesoexample.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public record QuesoRecipeData(
        Recipe.CommonInfo commonInfo,
        CraftingRecipe.CraftingBookInfo bookInfo,
        ShapedRecipePattern pattern,
        ItemStackTemplate result
) {
    public static final MapCodec<QuesoRecipeData> MAP_CODEC =
            RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            Recipe.CommonInfo.MAP_CODEC
                                    .forGetter(QuesoRecipeData::commonInfo),

                            CraftingRecipe.CraftingBookInfo.MAP_CODEC
                                    .forGetter(QuesoRecipeData::bookInfo),

                            ShapedRecipePattern.MAP_CODEC
                                    .forGetter(QuesoRecipeData::pattern),

                            ItemStackTemplate.CODEC
                                    .fieldOf("result")
                                    .forGetter(QuesoRecipeData::result)

                    ).apply(instance, QuesoRecipeData::new)
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, QuesoRecipeData> STREAM_CODEC =
            StreamCodec.composite(
                    Recipe.CommonInfo.STREAM_CODEC,
                    QuesoRecipeData::commonInfo,

                    CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
                    QuesoRecipeData::bookInfo,

                    ShapedRecipePattern.STREAM_CODEC,
                    QuesoRecipeData::pattern,

                    ItemStackTemplate.STREAM_CODEC,
                    QuesoRecipeData::result,

                    QuesoRecipeData::new
            );
}