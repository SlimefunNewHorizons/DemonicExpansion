package tsp.demonicexpansion.implementation.entity;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityManager {

    private final Map<NamespacedKey, DemonicEntity> entities = new ConcurrentHashMap<>();
    private final Map<UUID, ActiveDemonicEntity> activeEntities = new ConcurrentHashMap<>();

    public void register(NamespacedKey key, DemonicEntity entity) {
        entities.put(key, entity);
    }

    public Optional<DemonicEntity> getEntity(NamespacedKey key) {
        return Optional.ofNullable(entities.get(key));
    }

    /**
     * Adds a loaded entity to the ticker only when its persistent identifier is registered.
     */
    public void track(LivingEntity entity) {
        PersistentDataAPI.getOptionalString(entity, DemonicEntity.ENTITY_ID).ifPresent(id -> {
            NamespacedKey key = NamespacedKey.fromString(id);
            if (key != null) {
                getEntity(key).ifPresent(type -> activeEntities.put(
                    entity.getUniqueId(),
                    new ActiveDemonicEntity(entity, type)
                ));
            }
        });
    }

    public void untrack(UUID entityId) {
        activeEntities.remove(entityId);
    }

    /**
     * Ticks only loaded demonic entities and discards stale Bukkit references.
     */
    public void tickActiveEntities() {
        activeEntities.entrySet().removeIf(entry -> {
            LivingEntity entity = entry.getValue().entity();
            if (!entity.isValid() || entity.isDead()) {
                return true;
            }

            entry.getValue().type().tick(entity);
            return false;
        });
    }

    int getActiveEntityCount() {
        return activeEntities.size();
    }

    private record ActiveDemonicEntity(LivingEntity entity, DemonicEntity type) {}

}
