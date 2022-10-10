package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.config.FileManager;
import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.Specialarrows;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class SnowballArrow extends SpecialArrow {
    public SnowballArrow() {
        super("Snowball Arrow", "This arrow will spawn a snowball around the location it hits");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        FileManager.Config config = Specialarrows.getInstance().getFileManager().getConfig("config.yml");
        //create ice around hit location
        event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1, 1);
        event.getEntity().getWorld().spawnParticle(org.bukkit.Particle.SNOW_SHOVEL, event.getEntity().getLocation(), 10);
        circle(event.getEntity().getLocation(),config.get().getInt("arrows.snowballarrow.removeTime",20*5), Material.SNOW_BLOCK);
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
                            block.setType(material);
                            Bukkit.getScheduler().runTaskLater(Specialarrows.getInstance(), () -> block.setType(Material.AIR), 20*5);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.WHITE + "Snowball Arrow").lore("§7" + getDescription()).build();
    }
}
