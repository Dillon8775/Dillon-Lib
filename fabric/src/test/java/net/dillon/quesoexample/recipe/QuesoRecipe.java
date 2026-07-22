package net.dillon.quesoexample.recipe;

import net.dillon.dillonlib.factory.recipe.RecipeSerializerFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class QuesoRecipe extends CustomRecipe {
    public static final RecipeSerializer<QuesoRecipe> SERIALIZER =
            RecipeSerializerFactory.create(
                    QuesoRecipeData.MAP_CODEC,
                    QuesoRecipeData.STREAM_CODEC,
                    QuesoRecipe::new,
                    QuesoRecipe::getData
            );

    private final QuesoRecipeData data;

    public QuesoRecipe(QuesoRecipeData data) {
        this.data = data;
    }

    public QuesoRecipeData getData() {
        return this.data;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        // Your matching logic here
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return data.result().apply(
                net.minecraft.core.component.DataComponentPatch.EMPTY
        );
    }

    @Override
    public RecipeSerializer<QuesoRecipe> getSerializer() {
        return SERIALIZER;
    }
}