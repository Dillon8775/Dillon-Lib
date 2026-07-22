package net.dillon.dillonlib.factory.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Function;

@Deprecated
public class RecipeSerializerFactory {

    /**
     * Creates a recipe serializer from a recipe data codec.
     *
     * @param mapCodec the codec used to serialize the recipe to JSON
     * @param streamCodec the codec used to serialize the recipe over the network
     * @param constructor creates a recipe from its data
     * @param dataGetter gets the data from a recipe
     * @param <R> the recipe type
     * @param <D> the recipe data type
     * @return a recipe serializer
     */
    public static <R extends Recipe<?>, D> RecipeSerializer<R> create(
            MapCodec<D> mapCodec,
            StreamCodec<RegistryFriendlyByteBuf, D> streamCodec,
            Function<D, R> constructor,
            Function<R, D> dataGetter
    ) {
        MapCodec<R> recipeMapCodec = mapCodec.xmap(
                constructor,
                dataGetter
        );

        StreamCodec<RegistryFriendlyByteBuf, R> recipeStreamCodec = StreamCodec.of(
                (buffer, recipe) -> streamCodec.encode(buffer, dataGetter.apply(recipe)),
                buffer -> constructor.apply(streamCodec.decode(buffer))
        );

        return new RecipeSerializer<>(
                recipeMapCodec,
                recipeStreamCodec
        );
    }
}