package dev.gameu.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class settrophyslot implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("GameU.admin")){
            if(args.length == 0){
                sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Please specify a player and a item slot to remove");
                return true;
            }else if(args.length == 1){
                sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Please specify an item slot to remove");
                return true;
            }
            Player specifiedPlayer = Bukkit.getPlayer(args[0]);
            int slot = Integer.valueOf(args[1]);
            if(ViewTrohpy.menus.get(specifiedPlayer.getUniqueId().toString())[slot - 1] == null){
                sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "This slot is already empty!");
                return true;
            }else{
                ViewTrohpy.menus.get(specifiedPlayer.getUniqueId().toString())[slot - 1] = null;
                sender.sendMessage(ChatColor.GREEN + "Cleared slot " + slot + " from " + specifiedPlayer.getName() + "'s Tropies gui.");
                return true;
            }
        }else{
            ///
            return true;
        }
    }
}
