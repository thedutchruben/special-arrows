package nl.thedutchruben.specialarrows;

import nl.thedutchruben.mccore.Mccore;
import nl.thedutchruben.mccore.spigot.commands.CommandRegistry;
import nl.thedutchruben.mccore.config.UpdateCheckerConfig;
import nl.thedutchruben.mccore.utils.config.FileManager;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import nl.thedutchruben.specialarrows.arrows.types.*;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
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
        Metrics metrics = new Metrics(this, 16620);
        instance = this;
        FileManager.Config config = fileManager.getConfig("config.yml");
        FileConfiguration configfileConfiguration = config.get();
        configfileConfiguration.addDefault("settings.update_check", true);
        configfileConfiguration.addDefault("settings.update_checktime", 20*60*5);

        configfileConfiguration.addDefault("arrows.explosionarrow.blockDamage", true);
        configfileConfiguration.addDefault("arrows.explosionarrow.fire", true);
        configfileConfiguration.addDefault("arrows.explosionarrow.power", 3);
        configfileConfiguration.addDefault("arrows.explosionarrow.particle.count", 3);
        configfileConfiguration.addDefault("arrows.firewarrow.radius", 5);
        configfileConfiguration.addDefault("arrows.firewarrow.sound.volume", 3);
        configfileConfiguration.addDefault("arrows.firewarrow.paricle.count", 3);

        configfileConfiguration.addDefault("arrows.snowballarrow.removeTime", 20*5);
        configfileConfiguration.addDefault("arrows.bombarrow.arrows", 5);
        configfileConfiguration.addDefault("arrows.bombarrow.time", 20 * 1.5);

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

        if(configfileConfiguration.getBoolean("settings.update_check")) {
            mccore.startUpdateChecker(new UpdateCheckerConfig("specialarrows.update",configfileConfiguration.getInt("settings.update_checktime")));
        }

        metrics.addCustomChart(new SimplePie("download_source", DownloadSource.BUKKIT::name));
        metrics.addCustomChart(new SimplePie("update_checker", () ->  configfileConfiguration.getBoolean("settings.update_check",true) ? "enabled" : "disabled"));
        metrics.addCustomChart(new SimplePie("explosionarrow_blockdamage", () ->  configfileConfiguration.getBoolean("arrows.explosionarrow.blockDamage",true) ? "enabled" : "disabled"));

    }

    public FileManager getFileManager() {
        return fileManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        arrows.clear();

    }

    public List<SpecialArrow> getArrows() {
        return arrows;
    }

    public static Specialarrows getInstance() {
        return instance;
    }

    enum DownloadSource {
        SPIGOT,
        BUKKIT,
        GITHUB
    }
}
