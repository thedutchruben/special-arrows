package nl.thedutchruben.specialarrows.arrows.commands;

import nl.thedutchruben.mccore.spigot.commands.Command;
import nl.thedutchruben.mccore.spigot.commands.SubCommand;
import nl.thedutchruben.specialarrows.Specialarrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Command(command = "specialarrows", description = "This command will give you all the special arrows")
public class SpecialArrowCommand {

    @SubCommand(subCommand = "give", usage = "<player> <specialarrows> <amount>", minParams = 4,maxParams = 4)
    public void give(CommandSender sender, List<String> args) {
        Player player = Bukkit.getPlayer(args.get(1));
        Specialarrows.getInstance().getArrows().stream().filter(arrow -> ChatColor.stripColor(arrow.getName()).equalsIgnoreCase(args.get(2).replace("_"," "))).forEach(arrow -> {
            ItemStack itemStack = arrow.getItem().clone();
            itemStack.setAmount(Integer.parseInt(args.get(3)));
            player.getInventory().addItem(itemStack);
        });
    }

    @SubCommand(subCommand = "get", usage = "<specialarrows>", minParams = 2,maxParams = 2)
    public void get(CommandSender player, List<String> params) {
        Specialarrows.getInstance().getArrows().stream().filter(arrow -> ChatColor.stripColor(arrow.getName()).equalsIgnoreCase(params.get(1).replace("_"," "))).forEach(arrow -> ((Player)player).getInventory().addItem(arrow.getItem()));
    }
}
