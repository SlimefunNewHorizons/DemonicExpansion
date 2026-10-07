package tsp.demonicexpansion.implementation.recipe;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

public record DemonicRecipe(RecipeType type, ItemStack[] recipe) {}