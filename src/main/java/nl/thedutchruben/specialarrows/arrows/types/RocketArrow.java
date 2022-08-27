package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class RocketArrow extends SpecialArrow {
    public RocketArrow() {
        super("Rocket Arrow", "This arrow will launch the entity it hits in the air");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        if(arrow.getShooter() instanceof Player) {
            Entity player = event.getHitEntity();
            if(player != null){
                if(player instanceof Damageable){
                    spawnFireworks(player.getLocation(),0);
                    ((Damageable)player).damage(0);
                    player.setVelocity(new Vector(0, 2, 0));
                }

            }
        }
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.DARK_RED + "Rocket "+ChatColor.WHITE +"Arrow").lore("§7" + getDescription()).build();
    }

    public static void spawnFireworks(Location location, int amount){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        fw.getScoreboardTags().add("tdr-no-damage");
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();
    }
}
