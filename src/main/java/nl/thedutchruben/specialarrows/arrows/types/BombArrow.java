package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.Specialarrows;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Random;

public class BombArrow extends SpecialArrow {
    private BukkitTask task;
    public BombArrow() {
        super("Bomb Arrow", "This arrow will explode after 1.5 seconds and releases 5 more arrows");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        if(task != null){
            task.cancel();
            Location arrowLocation = event.getEntity().getLocation().clone().add(new Vector(0,5,0));
            for (int i = 0; i < 5; i++) {
                Arrow newArrow = arrow.getWorld().spawnArrow(arrowLocation, new Vector(0,0,0), 1.0F, 5.0F);
                newArrow.setShooter(arrow.getShooter());
            }
        }
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {
        task = Bukkit.getScheduler().runTaskLater(Specialarrows.getInstance(), () -> {
            for (int i = 0; i < 5; i++) {
                Arrow newArrow = arrow.getWorld().spawnArrow(arrow.getLocation(), arrow.getVelocity(), 1.0F, 5.0F);
                newArrow.setVelocity(arrow.getVelocity());
                newArrow.setShooter(player);
            }
            arrow.remove();
        }, (long) (20 * 1.5));
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.BOLD+ ""
                 + ChatColor.DARK_RED + "Bomb Arrow").lore("§7" + getDescription()).build();
    }

}
