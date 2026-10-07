package tsp.demonicexpansion.listener;

import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.entity.Entities;
import tsp.demonicexpansion.implementation.entity.DemonicEntity;
import cl.jackstar.smartplugin.handler.Handler;
import cl.jackstar.smartplugin.utils.NumberUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import java.util.List;
import java.util.Set;

public class EntitySpawnListener extends Handler {

    private static final Set<String> PROTECTED_VANILLA_WORLDS = Set.of(
        "clasico",
        "clasico_nether",
        "clasico_the_end"
    );

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (isDemonicNether(event.getLocation().getWorld())
            && isEligibleReplacementSpawn(event.getSpawnReason())) {
            if (event.getEntity() instanceof WitherSkeleton) {
                NumberUtils.chance(5, s -> {
                    event.setCancelled(true);
                    Entities.REAPER.spawn(event.getLocation());
                }, ignored -> {});
            } else if (event.getEntity() instanceof MagmaCube) {
                NumberUtils.chance(50, s -> {
                    event.setCancelled(true);
                    Entities.VULCAN.spawn(event.getLocation());
                }, ignored -> {});
            } else if (event.getEntity() instanceof PigZombie) {
                NumberUtils.chance(70, s -> {
                    event.setCancelled(true);
                    Entities.NECROMANCER.spawn(event.getLocation());
                }, ignored -> {});
            } else if (event.getEntity() instanceof Hoglin) {
                NumberUtils.chance(50, s -> {
                    event.setCancelled(true);
                    Entities.NECROMANCER.spawn(event.getLocation());
                }, ignored -> {});
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onVulcanSplit(SlimeSplitEvent event) {
        if (event.getEntity() instanceof MagmaCube magmaCube && isVulcan(magmaCube)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        markLoadedVulcansNonCollidable(event.getChunk());
    }

    static boolean isEligibleReplacementSpawn(CreatureSpawnEvent.SpawnReason reason) {
        return reason == CreatureSpawnEvent.SpawnReason.NATURAL;
    }

    /**
     * DemonicExpansion mejora sólo los mundos de juego que lo permiten. El tipo
     * NETHER no basta: ``clasico_nether`` también es Nether, pero Clásico debe
     * mantenerse vanilla y no puede recibir reemplazos de Vulcan/Reaper.
     */
    static boolean isDemonicNether(World world) {
        if (world == null || world.getEnvironment() != World.Environment.NETHER) {
            return false;
        }
        return !isExcludedWorld(
            world.getName(),
            DemonicExpansion.getInstance().getConfig()
                .getStringList("entity-replacements.excluded-worlds")
        );
    }

    /** Comparación aislada para mantener la regla testeable sin arrancar Bukkit. */
    static boolean isExcludedWorldName(String worldName, String excludedWorld) {
        return worldName != null && excludedWorld != null
            && excludedWorld.equalsIgnoreCase(worldName);
    }

    /**
     * Clásico permanece vanilla aunque el archivo de configuración esté vacío,
     * corrupto o sea anterior a la opción excluded-worlds. La lista configurable
     * sólo puede ampliar esta protección, nunca retirar los mundos reservados.
     */
    static boolean isExcludedWorld(String worldName, List<String> configuredWorlds) {
        boolean protectedWorld = PROTECTED_VANILLA_WORLDS.stream()
            .anyMatch(excluded -> isExcludedWorldName(worldName, excluded));
        return protectedWorld || configuredWorlds != null && configuredWorlds.stream()
            .anyMatch(excluded -> isExcludedWorldName(worldName, excluded));
    }

    static boolean isLegacyVulcanName(String customName, boolean customNameVisible) {
        return customNameVisible
            && customName != null
            && "Vulcan".equalsIgnoreCase(ChatColor.stripColor(customName));
    }

    private static boolean isVulcan(MagmaCube entity) {
        String vulcanId = Entities.VULCAN.getKey().toString();
        boolean tagged = PersistentDataAPI.getOptionalString(entity, DemonicEntity.ENTITY_ID)
            .filter(vulcanId::equals)
            .isPresent();
        return tagged || isLegacyVulcanName(entity.getCustomName(), entity.isCustomNameVisible());
    }

    private static void markLoadedVulcansNonCollidable(Chunk chunk) {
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof MagmaCube magmaCube && isVulcan(magmaCube)) {
                magmaCube.setCollidable(false);
            }
        }
    }

}
