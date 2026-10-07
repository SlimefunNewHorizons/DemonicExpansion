package tsp.demonicexpansion.implementation.item.misc;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.AbstractItem;
import tsp.demonicexpansion.implementation.recipe.Recipes;

public class DemonicEssence extends AbstractItem implements RandomMobDrop {

    private final ItemSetting<Integer> chance = new IntRangeSetting(this, "drop-chance", 0, 100, 100); // TODO: change default after debug

    public DemonicEssence() {
        super(DemonicExpansion.getInstance().getItems().DEMONIC_ESSENCE, Recipes.DEMONIC_ESSENCE);
        addItemSettings(chance);
    }

    @Override
    public int getMobDropChance() {
        return chance.getValue();
    }

}
