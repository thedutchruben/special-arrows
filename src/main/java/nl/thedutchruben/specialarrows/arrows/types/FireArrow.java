package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class FireArrow extends SpecialArrow {
    public FireArrow() {
        super("Fire Arrow", "This arrow will set fire to blocks when it hits them");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        circle(arrow.getLocation(), 5, Material.FIRE);
        arrow.getWorld().playSound(arrow.getLocation(), "fire.ignite", 3, 1);
    }

   public void circle(Location blockLocation, int radius, Material material) {
        World world = blockLocation.getWorld();
        int x = blockLocation.getBlockX();
        int y = blockLocation.getBlockY();
        int z = blockLocation.getBlockZ();
        for (int x1 = x - radius; x1 <= x + radius; x1++) {
            for (int z1 = z - radius; z1 <= z + radius; z1++) {
                for (int y1 = y - radius; y1 <= y + radius; y1++) {
                    double distance = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) + (z1 - z) * (z1 - z);
                    if (distance < radius * radius) {
                        Block block = world.getBlockAt(x1, y1, z1);
                        if (block.getType() == Material.AIR) {
                            blockLocation.getWorld().spawnParticle(org.bukkit.Particle.FLAME, blockLocation, 5);
                            block.setType(material);
                        }
                    }
                }
            }
        }
   }

    @Override
    public void onShoot(Arrow arrow, Player player) {
        player.getLocation().getWorld().playSound(player.getLocation(), "fire.ignite", 3, 1);
        player.getLocation().getWorld().spawnParticle(org.bukkit.Particle.FLAME, player.getLocation(), 10, 1, 1, 1, 0.5);
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.DARK_RED + "Fire Arrow").lore("§7" + getDescription()).build();
    }
}
