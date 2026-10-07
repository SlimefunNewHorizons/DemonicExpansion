package tsp.demonicexpansion.implementation.item.weapon;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.Unburnable;
import tsp.demonicexpansion.implementation.recipe.Recipes;
import tsp.demonicexpansion.util.Utils;
import cl.jackstar.smartplugin.player.PlayerUtils;
import cl.jackstar.smartplugin.utils.ItemUtils;

import java.util.Arrays;
import java.util.List;

public class DevilsRing extends DemonicWeapon implements Unburnable {

    public DevilsRing() {
        super(DemonicExpansion.getInstance().getItems().DEVILS_RING, Recipes.DEVILS_RING);
    }

    private final List<PotionEffect> effects = Arrays.asList(
            new PotionEffect(PotionEffectType.BLINDNESS, 100, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 100, 0)
    );

    @Override
    public void onRightClick(PlayerRightClickEvent event) {
        ItemUtils.use(event.getItem(), 60, cd -> {
            for (LivingEntity entity : Utils.getNearbyLivingEntities(event.getPlayer(), 5)) {
                entity.addPotionEffects(effects);
                entity.setFireTicks(40);
            }
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1f, 1f);
        }, left -> PlayerUtils.sendMessage(event.getPlayer(), "&cYou cannot use this yet: &e" + left + "s")); // No sound because it would be deafening when spammed on cooldown.
    }

}
