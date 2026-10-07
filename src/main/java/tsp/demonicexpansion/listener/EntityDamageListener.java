package tsp.demonicexpansion.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import tsp.demonicexpansion.DemonicExpansion;
import tsp.demonicexpansion.implementation.entity.DemonicEntity;
import tsp.demonicexpansion.implementation.entity.EntityManager;
import tsp.demonicexpansion.implementation.item.weapon.DemonicWeapon;
import cl.jackstar.smartplugin.event.LivingEntityDamageByLivingEntityEvent;
import cl.jackstar.smartplugin.handler.Handler;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

public class EntityDamageListener extends Handler {

    @EventHandler
    public void onEntityHit(LivingEntityDamageByLivingEntityEvent event) {
        EntityManager manager = DemonicExpansion.getInstance().getEntityManager();
        DemonicExpansion.getInstance().getLog().trace("fired damage listener! Damager: " + event.getDamager().getName() + " | Victim: " + event.getEntity().getName());

        // onDamageDealt - Damager
        PersistentDataAPI.getOptionalString(event.getDamager(), DemonicEntity.ENTITY_ID)
                .flatMap(id -> manager.getEntity(NamespacedKey.fromString(id)))
                .ifPresent(entity -> entity.onDamageDealt(event));

        // onDamageReceived - Victim
        PersistentDataAPI.getOptionalString(event.getVictim(), DemonicEntity.ENTITY_ID)
                .flatMap(id -> manager.getEntity(NamespacedKey.fromString(id)))
                .ifPresent(entity -> entity.onDamageReceived(event));

        // onHit - DemonicWeapon
        EntityEquipment damagerEquipment = event.getDamager().getEquipment();
        if (damagerEquipment != null) {
            ItemStack hand = damagerEquipment.getItemInMainHand();
            if (SlimefunItem.getByItem(hand) instanceof DemonicWeapon weapon) {
                weapon.onHit(event);
            }
        }
    }

}
