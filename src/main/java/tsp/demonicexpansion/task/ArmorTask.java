package tsp.demonicexpansion.task;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.item.armor.AbstractArmor;
import cl.jackstar.smartplugin.tasker.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This task ticks all {@link AbstractArmor armor pieces}.
 *
 * @author TheSilentPro (Silent)
 */
public class ArmorTask implements Task {

    private static final long MINIMUM_INTERVAL_TICKS = 5L;
    private final Map<UUID, BlockPosition> lastSensitivePositions = new HashMap<>();

    @Override
    public void run() {
        // Armor effects are player equipment. Scanning every living entity in every world made
        // this task scale with farms and loaded chunks instead of actual users.
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID playerId = player.getUniqueId();

            EntityEquipment equipment = player.getEquipment();
            BlockPosition currentPosition = BlockPosition.from(player);
            BlockPosition previousPosition = lastSensitivePositions.get(playerId);
            boolean hasPositionSensitiveArmor = false;

            for (ItemStack armor : equipment.getArmorContents()) {
                if (armor == null || armor.getType().isAir()) {
                    continue;
                }

                SlimefunItem item = SlimefunItem.getByItem(armor);
                if (item instanceof AbstractArmor demonicArmor) {
                    if (demonicArmor.requiresPositionChange()) {
                        hasPositionSensitiveArmor = true;
                        if (!currentPosition.equals(previousPosition)) {
                            demonicArmor.whileWearing(player);
                        }
                    } else {
                        demonicArmor.whileWearing(player);
                    }
                }
            }

            if (hasPositionSensitiveArmor) {
                lastSensitivePositions.put(playerId, currentPosition);
            } else {
                lastSensitivePositions.remove(playerId);
            }
        }

        lastSensitivePositions.keySet().removeIf(playerId -> Bukkit.getPlayer(playerId) == null);
    }

    @Override
    public long getRepeatInterval() {
        long configured = DemonicExpansion.getInstance().getConfig().getLong("ticker.armor", MINIMUM_INTERVAL_TICKS);
        return normalizeInterval(configured);
    }

    static long normalizeInterval(long configured) {
        return Math.max(MINIMUM_INTERVAL_TICKS, configured);
    }

    private record BlockPosition(UUID worldId, int x, int y, int z) {

        private static BlockPosition from(Player player) {
            Location location = player.getLocation();
            return new BlockPosition(
                player.getWorld().getUID(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
            );
        }
    }

}
