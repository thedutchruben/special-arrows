package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportArrow extends SpecialArrow {
    public TeleportArrow() {
        super("Teleport Arrow", "This arrow will teleport you to the location it hits");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {

        if(arrow.getShooter() instanceof Player) {
            Player player = ((Player) arrow.getShooter());
            player.getWorld().spawnParticle(org.bukkit.Particle.PORTAL, player.getLocation(), 10);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            ((Player) arrow.getShooter()).teleport(event.getEntity().getLocation());
        }
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {
        player.getWorld().spawnParticle(org.bukkit.Particle.PORTAL, player.getLocation(), 10);
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname("§bTeleport Arrow").lore("§7" + getDescription()).build();
    }
}
