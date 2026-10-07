package tsp.demonicexpansion.implementation.item.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import tsp.demonicexpansion.implementation.item.AbstractItem;
import tsp.demonicexpansion.implementation.recipe.DemonicRecipe;
import cl.jackstar.smartplugin.event.LivingEntityDamageByLivingEntityEvent;

public class DemonicWeapon extends AbstractItem {

    public DemonicWeapon(SlimefunItemStack item, DemonicRecipe recipe) {
        super(item, recipe);
    }

    public void onHit(LivingEntityDamageByLivingEntityEvent event) {}

}
