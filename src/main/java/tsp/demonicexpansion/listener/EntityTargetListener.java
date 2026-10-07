package tsp.demonicexpansion.listener;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.entity.DemonicEntity;
import tsp.demonicexpansion.implementation.entity.Hellhound;
import cl.jackstar.smartplugin.handler.Handler;

public class EntityTargetListener extends Handler {

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        // Cancel if a hound tries to target a non-player
        PersistentDataAPI.getOptionalString(event.getEntity(), DemonicEntity.ENTITY_ID)
                .flatMap(id -> DemonicExpansion.getInstance().getEntityManager().getEntity(NamespacedKey.fromString(id)))
                .ifPresent(entity -> {
                    if (entity instanceof Hellhound) {
                        if (!(event.getTarget() instanceof Player)) {
                            event.setCancelled(true);
                        }
                    }
                });
    }

}
