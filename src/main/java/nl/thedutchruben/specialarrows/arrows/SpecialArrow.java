package nl.thedutchruben.specialarrows.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public abstract class SpecialArrow {

    private String name; // The name of the arrow
    private String description;   // The description of the arrow

    public SpecialArrow(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    abstract public void onHit(Arrow arrow, ProjectileHitEvent event);

    abstract public void onShoot(Arrow arrow, Player player);

    abstract public ItemStack getItem();

}
