package tsp.demonicexpansion.implementation.item;

import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import org.bukkit.Material;
import tsp.demonicexpansion.implementation.item.armor.DemonicBoots;
import tsp.demonicexpansion.implementation.item.armor.DemonicChestplate;
import tsp.demonicexpansion.implementation.item.armor.DemonicHelmet;
import tsp.demonicexpansion.implementation.item.armor.DemonicLeggings;
import tsp.demonicexpansion.implementation.item.generator.ThermalGenerator;
import tsp.demonicexpansion.implementation.item.misc.DemonicEssence;
import tsp.demonicexpansion.implementation.item.misc.Napalm;
import tsp.demonicexpansion.implementation.item.misc.PentecostalCoin;
import tsp.demonicexpansion.implementation.item.weapon.DevilsRing;

/**
 * Contains all the items that are used by this addon.
 */
public class Items {

    // Misc

    public final DemonicItemStack DEMONIC_ESSENCE = new DemonicItemStack(
            "DEMONIC_ESSENCE",
            Material.PURPLE_DYE,
            "&cDemonic Essence"
    );

    public final DemonicItemStack PENTECOSTAL_COIN = new DemonicItemStack(
            "PENTECOSTAL_COIN",
            Material.ORANGE_DYE,
            "&cPentecostal Coin",
            "",
            "&7Shift+Right Click: Link position to block (Overworld and Nether)",
            "&7Right Click: Teleport to the Nether and back (60s)"
    );

    public final DemonicItemStack NAPALM = new DemonicItemStack(
            "NAPALM",
            Material.GREEN_DYE,
            "&2Napalm"
    );

    // Armor

    public final DemonicItemStack DEMONIC_HELMET = new DemonicItemStack(
            "DEMONIC_HELMET",
            Material.NETHERITE_HELMET,
            "&cDemonic Helmet",
            "",
            "&6Glimpse: &7Grants &9Night Vision"
    );

    public final DemonicItemStack DEMONIC_CHESTPLATE = new DemonicItemStack(
            "DEMONIC_CHESTPLATE",
            Material.NETHERITE_CHESTPLATE,
            "&cDemonic Chestplate",
            "",
            "&6Concealment: &7Grants &6Fire Resistance"
    );

    public final DemonicItemStack DEMONIC_LEGGINGS = new DemonicItemStack(
            "DEMONIC_LEGGINGS",
            Material.NETHERITE_LEGGINGS,
            "&cDemonic Leggings",
            "",
            "&6Search: &7Grants &cRegeneration II"
    );

    public final DemonicItemStack DEMONIC_BOOTS = new DemonicItemStack(
            "DEMONIC_BOOTS",
            Material.NETHERITE_BOOTS,
            "&cDemonic Boots",
            "",
            "&6Lava Walker: &7Turns surrounding lava into obsidian permanently"
    );

    // Weapon

    public final DemonicItemStack DEVILS_RING = new DemonicItemStack(
            "DEVILS_RING",
            Material.RED_DYE,
            "&cDevil's Ring",
            "",
            "&6Active Ability: &7Blinds, weakens and ignites nearby enemies (60s)"
    );

    // Machines

    public final DemonicItemStack THERMAL_GENERATOR = new DemonicItemStack(
            "THERMAL_GENERATOR",
            HeadTexture.GENERATOR,
            "&cDemonic Thermal Generator",
            "",
            "&7Generates energy in the Nether",
            "&7Must be placed on lava"
    );

    public void setup() {
        new DemonicEssence().registerDefault();
        new DemonicHelmet().registerDefault();
        new DemonicChestplate().registerDefault();
        new DemonicLeggings().registerDefault();
        new DemonicBoots().registerDefault();
        new PentecostalCoin().registerDefault();
        new Napalm().registerDefault();
        new DevilsRing().registerDefault();
        new ThermalGenerator().registerDefault();
    }

}
