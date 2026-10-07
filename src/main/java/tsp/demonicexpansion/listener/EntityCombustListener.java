package tsp.demonicexpansion.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityCombustEvent;
import tsp.demonicexpansion.implementation.item.Unburnable;
import cl.jackstar.smartplugin.handler.Handler;

public class EntityCombustListener extends Handler {

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Item item) {
            if (SlimefunItem.getByItem(item.getItemStack()) instanceof Unburnable) {
                event.setCancelled(true);
            }
        }
    }

}
