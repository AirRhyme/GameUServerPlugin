package dev.gameu.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

public class addTrophy implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You are not a player and cannot view anything");
            return true;
        }
        Player p = (Player)sender;
        if(p.hasPermission("GameU.admin")) {
            if(args.length != 0){
                Player target = Bukkit.getPlayer(args[0]);
                ViewTrohpy.addTrophy(target, p.getInventory().getItemInMainHand(), p);
                p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Gave trophy " + p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().toString() + " to " + target.getName());
                return true;
            }
            p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Please specify a player");
            return true;
        }else {
            p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "You do not have permission");
            return true;
        }
    }
}
