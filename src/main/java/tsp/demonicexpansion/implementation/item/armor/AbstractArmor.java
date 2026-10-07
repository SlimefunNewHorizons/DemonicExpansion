package tsp.demonicexpansion.implementation.item.armor;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.LivingEntity;
import tsp.demonicexpansion.implementation.item.AbstractItem;
import tsp.demonicexpansion.implementation.recipe.DemonicRecipe;

public class AbstractArmor extends AbstractItem {

    public AbstractArmor(SlimefunItemStack item, DemonicRecipe recipe) {
        super(item, recipe);
    }

    /**
     * Indicates that this effect only needs recalculation after crossing a block boundary.
     */
    public boolean requiresPositionChange() {
        return false;
    }

    public void whileWearing(LivingEntity entity) {}

}
