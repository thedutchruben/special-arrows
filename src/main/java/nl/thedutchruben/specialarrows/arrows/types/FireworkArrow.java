package nl.thedutchruben.specialarrows.arrows.types;

import nl.thedutchruben.mccore.utils.config.FileManager;
import nl.thedutchruben.mccore.utils.item.ItemBuilder;
import nl.thedutchruben.specialarrows.Specialarrows;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class FireworkArrow extends SpecialArrow {

    public FireworkArrow() {
        super("Firework Arrow", "This arrow will spawn a firework");
    }

    @Override
    public void onHit(Arrow arrow, ProjectileHitEvent event) {
        spawnFireworks(event.getHitBlock().getLocation().add(0,1.5,0),new Random().nextInt(4) + 1);
    }

    @Override
    public void onShoot(Arrow arrow, Player player) {

    }

    public static void spawnFireworks(Location location, int amount){
        for(int i = 0;i<amount; i++){
            Location loc = location;
            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw.getScoreboardTags().add("tdr-no-damage");
            FireworkMeta fwm = fw.getFireworkMeta();

            fwm.setPower(new Random().nextInt(3) + 1);
            fwm.addEffect(FireworkEffect.builder().
                    with(FireworkEffect.Type.values()[new Random().nextInt(FireworkEffect.Type.values().length)]).
                    withFade(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255))).
                    withColor(Color.fromBGR(new Random().nextInt(255),
                            new Random().nextInt(255),
                            new Random().nextInt(255))).flicker(new Random().nextBoolean()).build());

            fw.setFireworkMeta(fwm);
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW).displayname(ChatColor.RED + "Firework "+ChatColor.WHITE+"Arrow").lore("§7" + getDescription()).build();
    }
}
