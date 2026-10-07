package tsp.demonicexpansion.implementation.item.generator;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.AbstractItem;
import tsp.demonicexpansion.implementation.recipe.Recipes;

import javax.annotation.Nonnull;

public class ThermalGenerator extends AbstractItem implements EnergyNetProvider {

    private final ItemSetting<Integer> energy = new IntRangeSetting(this, "energy-generation", 2, 2, Integer.MAX_VALUE);

    public ThermalGenerator() {
        super(DemonicExpansion.getInstance().getItems().THERMAL_GENERATOR, Recipes.THERMAL_GENERATOR);
        addItemSettings(energy);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location l, @Nonnull Config data) {
        World world = l.getWorld();
        if (world != null && world.getEnvironment() == World.Environment.NETHER && l.clone().subtract(0, 1, 0).getBlock().getType() == Material.LAVA) {
            return energy.getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int getCapacity() {
        return 0;
    }

}
