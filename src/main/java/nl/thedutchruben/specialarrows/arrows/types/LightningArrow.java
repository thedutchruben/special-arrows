package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class LightningArrow extends SpecialArrow {
    public LightningArrow() {
        super("Lightning Arrow", "This arrow will strike lightning when it hits a block or player");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        arrow.getWorld().strikeLightning(arrow.getLocation());
        arrow.getWorld().strikeLightningEffect(arrow.getLocation());
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.AQUA + "Lightning Arrow").lore("§7" + getDescription()).build();
    }
}
