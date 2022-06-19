package nl.thedutchruben.specialarrows;

import nl.thedutchruben.mccore.Mccore;
import nl.thedutchruben.mccore.commands.CommandRegistry;
import nl.thedutchruben.mccore.commands.TabComplete;
import nl.thedutchruben.mccore.config.UpdateCheckerConfig;
import nl.thedutchruben.specialarrows.arrows.SpecialArrow;
import nl.thedutchruben.specialarrows.arrows.types.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Specialarrows extends JavaPlugin {
    private List<SpecialArrow> arrows = new ArrayList<>();
    private static Specialarrows instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Mccore mccore = new Mccore(this,"specialarrows","62adf3511e02c441a5ce2b1a");
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
