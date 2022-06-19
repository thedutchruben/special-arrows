package nl.thedutchruben.specialarrows.arrows.listeners;

import nl.thedutchruben.mccore.listeners.TDRListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

@TDRListener
public class FireworkDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("tdr-no-damage")) {
            event.setCancelled(true);
        }
    }
}
