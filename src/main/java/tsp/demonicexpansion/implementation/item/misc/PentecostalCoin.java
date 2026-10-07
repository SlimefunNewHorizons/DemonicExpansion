package tsp.demonicexpansion.implementation.item.misc;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.meta.ItemMeta;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.AbstractItem;
import tsp.demonicexpansion.implementation.item.DemonicItemStack;
import tsp.demonicexpansion.implementation.recipe.Recipes;
import cl.jackstar.smartplugin.player.PlayerUtils;
import cl.jackstar.smartplugin.utils.ItemUtils;
import cl.jackstar.smartplugin.utils.SerializationUtils;

import java.util.Optional;

public class PentecostalCoin extends AbstractItem {

    private final NamespacedKey NORMAL_LOCATION = new NamespacedKey(DemonicExpansion.getInstance(), "anchor_normal");
    private final NamespacedKey NETHER_LOCATION = new NamespacedKey(DemonicExpansion.getInstance(), "anchor_nether");
    
    public PentecostalCoin() {
        super(DemonicExpansion.getInstance().getItems().PENTECOSTAL_COIN, Recipes.PENTECOSTAL_COIN);
    }

    @Override
    public void onRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        World.Environment environment = player.getWorld().getEnvironment();

        // If sneaking, handle anchor
        if (player.isSneaking()) {
            Optional<Block> block = event.getClickedBlock();
            if (block.isEmpty()) {
                PlayerUtils.sendMessage(player, "&cYou must right click on the block you want to link!");
                return;
            }

            NamespacedKey key = null;
            if (environment == World.Environment.NORMAL) {
                key = NORMAL_LOCATION;
            } else if (environment == World.Environment.NETHER) {
                key = NETHER_LOCATION;
            }

            if (key != null) {
                ItemMeta meta = event.getItem().getItemMeta();
                Location location = block.get().getLocation();
                PersistentDataAPI.setString(meta, key, SerializationUtils.serializeLocation(location));
                event.getItem().setItemMeta(meta);
                PlayerUtils.sendMessage(player, "&7Position linked to: &ex=" + location.getBlockX() + " y=" + location.getBlockY() + " z=" + location.getBlockZ());
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            } else {
                PlayerUtils.sendMessage(player, "&cYou cannot link a position in this world!");
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
            }
            return;
        }

        // Not sneaking, handle teleportation (to other location, hence inversion)
        NamespacedKey key = null;
        if (environment == World.Environment.NORMAL) {
            key = NETHER_LOCATION;
        } else if (environment == World.Environment.NETHER){
            key = NORMAL_LOCATION;
        }

        if (key != null) {
            Optional<String> serialized = PersistentDataAPI.getOptionalString(event.getItem().getItemMeta(), key);
            if (serialized.isPresent()) {
                ItemUtils.use(event.getItem(), 60, cd -> {
                    if (PaperLib.isPaper()) {
                        PaperLib.teleportAsync(player, SerializationUtils.deserializeLocation(serialized.get()), PlayerTeleportEvent.TeleportCause.PLUGIN).whenComplete((r, ex) -> {
                            PlayerUtils.sendMessage(player, "&5Here we go!");
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                        });
                    } else {
                        player.teleport(SerializationUtils.deserializeLocation(serialized.get()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    }
                }, left -> PlayerUtils.sendMessage(event.getPlayer(), "&cYou cannot use this yet: &e" + left + "s"));
            } else {
                PlayerUtils.sendMessage(player, "&cYou have not linked a return position yet!");
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
            }
        } else {
            PlayerUtils.sendMessage(player, "&cThis item cannot be used in this world!");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
        }
    }
    
}
