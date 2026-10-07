package tsp.demonicexpansion.implementation.recipe;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.Items;

public final class Recipes {

    private Recipes() {}

    private static final Items items = DemonicExpansion.getInstance().getItems();

    // Miscellaneous

    public static final DemonicRecipe DEMONIC_ESSENCE = new DemonicRecipe(RecipeType.MOB_DROP, new ItemStack[]{
            null, null, null,
            null, new CustomItemStack(Material.WITHER_SKELETON_SKULL, "Wither Skeleton"), null,
            null, null, null
    });

    public static final DemonicRecipe PENTECOSTAL_COIN = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            items.DEMONIC_ESSENCE, SlimefunItems.ENRICHED_NETHER_ICE, items.DEMONIC_ESSENCE,
            SlimefunItems.STRANGE_NETHER_GOO, SlimefunItems.PORTABLE_TELEPORTER, SlimefunItems.STRANGE_NETHER_GOO,
            items.DEMONIC_ESSENCE, SlimefunItems.ENRICHED_NETHER_ICE, items.DEMONIC_ESSENCE
    });

    public static final DemonicRecipe NAPALM = new DemonicRecipe(RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.SILICON, new ItemStack(Material.TNT), SlimefunItems.SILICON,
            new ItemStack(Material.TNT), SlimefunItems.FUEL_BUCKET, new ItemStack(Material.TNT),
            SlimefunItems.SILICON, new ItemStack(Material.TNT), SlimefunItems.SILICON
    });

    // Armor

    public static final DemonicRecipe DEMONIC_HELMET = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, new ItemStack(Material.NETHERITE_HELMET), items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE
    });

    public static final DemonicRecipe DEMONIC_CHESTPLATE = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, new ItemStack(Material.NETHERITE_CHESTPLATE), items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE
    });

    public static final DemonicRecipe DEMONIC_LEGGINGS = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, new ItemStack(Material.NETHERITE_LEGGINGS), items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE
    });

    public static final DemonicRecipe DEMONIC_BOOTS = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, new ItemStack(Material.NETHERITE_BOOTS), items.DEMONIC_ESSENCE,
            items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE, items.DEMONIC_ESSENCE
    });

    // Weapons

    public static final DemonicRecipe DEVILS_RING = new DemonicRecipe(RecipeType.ANCIENT_ALTAR, new ItemStack[]{
            SlimefunItems.STRANGE_NETHER_GOO, items.DEMONIC_ESSENCE, SlimefunItems.STRANGE_NETHER_GOO,
            items.DEMONIC_ESSENCE, items.NAPALM, items.DEMONIC_ESSENCE,
            SlimefunItems.STRANGE_NETHER_GOO, items.DEMONIC_ESSENCE, SlimefunItems.STRANGE_NETHER_GOO
    }); // TODO: better recipe

    // Machines

    public static final DemonicRecipe THERMAL_GENERATOR = new DemonicRecipe(RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.NETHER_ICE_COOLANT_CELL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.NETHER_ICE_COOLANT_CELL,
            SlimefunItems.LAVA_CRYSTAL, SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.LAVA_CRYSTAL,
            SlimefunItems.NETHER_ICE_COOLANT_CELL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.NETHER_ICE_COOLANT_CELL
    });

}
