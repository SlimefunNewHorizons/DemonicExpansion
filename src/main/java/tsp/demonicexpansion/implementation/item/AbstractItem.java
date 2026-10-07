package tsp.demonicexpansion.implementation.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.group.Groups;
import tsp.demonicexpansion.implementation.recipe.DemonicRecipe;

import java.util.List;

public class AbstractItem extends SlimefunItem {

    private List<ItemSetting<?>> settings = null;

    public AbstractItem(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(Groups.MAIN, item, recipeType, recipe);
    }

    public AbstractItem(SlimefunItemStack item, DemonicRecipe recipe) {
        super(Groups.MAIN, item, recipe.type(), recipe.recipe());
    }

    public void onRightClick(PlayerRightClickEvent event) {}

    public void addItemSettings(ItemSetting<?>... settings) {
        this.settings = List.of(settings);
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onRightClick);
        if (settings != null) {
            addItemSetting(settings.toArray(new ItemSetting[0]));
        }
    }

    public void registerDefault() {
        register(DemonicExpansion.getInstance());
    }

}
