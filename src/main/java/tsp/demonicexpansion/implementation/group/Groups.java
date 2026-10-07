package tsp.demonicexpansion.implementation.group;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import tsp.demonicexpansion.DemonicExpansion;

public final class Groups {

    private Groups() {}

    public static final ItemGroup MAIN = new ItemGroup(
            new NamespacedKey(DemonicExpansion.getInstance(), "group_main"),
            new CustomItemStack(Material.MAGMA_CREAM, "&4Demonic Expansion", "", "&7Items")
    );

}
