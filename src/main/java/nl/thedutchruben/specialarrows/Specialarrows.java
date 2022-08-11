package nl.thedutchruben.specialarrows;

import nl.thedutchruben.mccore.Mccore;
import nl.thedutchruben.mccore.spigot.commands.CommandRegistry;
import nl.thedutchruben.mccore.config.UpdateCheckerConfig;
import nl.thedutchruben.mccore.utils.config.FileManager;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import nl.thedutchruben.specialarrows.arrows.types.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Specialarrows extends JavaPlugin {
    private List<SpecialArrow> arrows = new ArrayList<>();
    private static Specialarrows instance;
    private FileManager fileManager = new FileManager(this);
    @Override
    public void onEnable() {
        instance = this;
        FileManager.Config config = fileManager.getConfig("config.yml");
        FileConfiguration configfileConfiguration = config.get();
        configfileConfiguration.options().header("TDR Playtime Plugin " +
                "\nhttps://www.spigotmc.org/resources/tdrplaytime.47894/ \n" +
                "Change the language to one of the other files default it has nl_NL.yml and en_GB.yml, " +
                "you can create your own language file");
        configfileConfiguration.addDefault("settings.update_check", true);
        configfileConfiguration.addDefault("settings.update_checktime", 20*60*5);
        configfileConfiguration.addDefault("arrows.explosionarrow.blockDamage", true);
        configfileConfiguration.addDefault("arrows.explosionarrow.fire", true);
        configfileConfiguration.addDefault("arrows.explosionarrow.range", 5);
        configfileConfiguration.addDefault("arrows.snowballarrow.removeTime", 20*5);

        config.copyDefaults(true).save();
        // Plugin startup logic
        Mccore mccore = new Mccore(this,"specialarrows","62adf3511e02c441a5ce2b1a", Mccore.PluginType.SPIGOT);

        arrows.add(new ExplosionArrow());
        arrows.add(new FireArrow());
        arrows.add(new RocketArrow());
        arrows.add(new LightningArrow());
        arrows.add(new BombArrow());
        arrows.add(new TreeArrow());
        arrows.add(new TeleportArrow());
        arrows.add(new FireworkArrow());
        arrows.add(new SnowballArrow());

        CommandRegistry.getTabCompletable().put("specialarrows", commandSender -> {
            Set<String> completions = new HashSet<>();
            for (SpecialArrow arrow : arrows) {
                completions.add(ChatColor.stripColor(arrow.getName()));
            }
            return completions;
        });

        mccore.startUpdateChecker(new UpdateCheckerConfig("specialarrows.update",5*60*20));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<SpecialArrow> getArrows() {
        return arrows;
    }

    public static Specialarrows getInstance() {
        return instance;
    }
}
