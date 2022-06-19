package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.TreeType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class TreeArrow extends SpecialArrow {
    public TreeArrow() {
        super("Tree Arrow", "This arrow will create a tree when it hits a block or player");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        arrow.getWorld().generateTree(arrow.getLocation(), getRandomTree());
        arrow.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, arrow.getLocation(), 10, 0.5, 0.5, 0.5);
    }

    public TreeType getRandomTree()  {
        return TreeType.values()[(new Random().nextInt(TreeType.values().length))];
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname("§bTree Arrow").lore("§7" + getDescription()).build();
    }
}
