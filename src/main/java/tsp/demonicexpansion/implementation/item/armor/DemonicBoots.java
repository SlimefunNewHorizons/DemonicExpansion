package tsp.demonicexpansion.implementation.item.armor;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.recipe.Recipes;

public class DemonicBoots extends DemonicArmor {

    private static final int MAXIMUM_SAFE_RADIUS = 8;
    private final ItemSetting<Integer> radiusSetting = new IntRangeSetting(this, "radius", 1, 2, MAXIMUM_SAFE_RADIUS);

    public DemonicBoots() {
        super(DemonicExpansion.getInstance().getItems().DEMONIC_BOOTS, Recipes.DEMONIC_BOOTS);
        addItemSettings(radiusSetting);
    }

    @Override
    public boolean requiresPositionChange() {
        return true;
    }

    @Override
    public void whileWearing(LivingEntity entity) {
        Block stoodBlock = entity.getLocation().clone().subtract(0, 1, 0).getBlock();
        int radius = normalizeRadius(radiusSetting.getValue());

        // Thanks Sefiraat for this part, heavily tweaked to fit this addon
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Block b = stoodBlock.getRelative(x, 0, z);

                // TODO: Find a way to stop entities from possibly being able to use this to grief builds with this
                if (entity instanceof Player p) {
                    if (!Slimefun.getProtectionManager().hasPermission(p, b, Interaction.PLACE_BLOCK)) {
                        continue;
                    }

                    if (b.getType() == Material.LAVA && b.getBlockData() instanceof Levelled l) {
                        if (l.getLevel() == 0) {
                            b.setType(Material.OBSIDIAN, false);
                            // The obsidian doesn't turn back into lava, this is intentional!
                        }
                    }
                }
                // Lava walker will only work on players, it is *possible* that entities can be abused to grief with it.
            }
        }
    }

    static int normalizeRadius(int configured) {
        return Math.max(1, Math.min(MAXIMUM_SAFE_RADIUS, configured));
    }

}
