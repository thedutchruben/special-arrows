package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.config.FileManager;
import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.Specialarrows;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class ExplosionArrow extends SpecialArrow {
    private FileManager.Config config = Specialarrows.getInstance().getFileManager().getConfig("config.yml");

    public ExplosionArrow() {
        super("Explosion Arrow", "This arrow will explode when it hits a block or player");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {

        arrow.getWorld().createExplosion(arrow.getLocation(), config.get().getInt("arrows.explosionarrow.power",3), config.get().getBoolean("arrows.explosionarrow.fire",true),
                config.get().getBoolean("arrows.explosionarrow.blockDamage",true));
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {
        arrow.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, arrow.getLocation(), config.get().getInt("arrows.explosionarrow.particle.count",3));
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.RED + "Explosion Arrow").lore("§7" + getDescription()).build();
    }
}
