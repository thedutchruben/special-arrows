package nl.thedutchruben.specialarrows.arrows.listeners;

import nl.thedutchruben.mccore.spigot.listeners.TDRListener;
import nl.thedutchruben.specialarrows.Specialarrows;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

@TDRListener
public class OnArrowShoot implements Listener {

    @EventHandler
    public void onShootEvent(EntityShootBowEvent event) {
        if(event.getConsumable() != null){
            if(event.getConsumable().hasItemMeta()){
                if(event.getConsumable().getItemMeta().hasDisplayName()){
                    for (SpecialArrow arrow : Specialarrows.getInstance().getArrows()) {
                        if(ChatColor.stripColor(event.getConsumable().getItemMeta().getDisplayName()).equals(ChatColor.stripColor(arrow.getItem().getItemMeta().getDisplayName()))){
                            arrow.onShoot((Arrow) event.getProjectile(), (Player) event.getEntity());
                            event.getProjectile().getScoreboardTags().add(ChatColor.stripColor(arrow.getName()));
                        }
                    }

                }
            }
        }
    }
    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event)
    {
        if(event.getEntity() instanceof Arrow){
                for (SpecialArrow arrow : Specialarrows.getInstance().getArrows()) {
                    if( event.getEntity().getScoreboardTags().contains(ChatColor.stripColor(arrow.getName()))){
                        arrow.onHit((Arrow) event.getEntity(),event);
                        event.getEntity().getScoreboardTags().remove(ChatColor.stripColor(arrow.getName()));
                    }
                }

        }
    }
}
