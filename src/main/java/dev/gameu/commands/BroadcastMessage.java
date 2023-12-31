package dev.gameu.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastMessage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 0){
            commandSender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Please provide a message to send");
            return true;
        }

        if(commandSender.hasPermission("GameU.admin")){
            String message = String.join(" ", args);

            // Send the message to all players on the server
            Bukkit.broadcastMessage("§7[§4GAME U ANNOUNCEMENT§7] §r" + message);
            commandSender.sendMessage(ChatColor.GREEN + "Success!");
            return true;
        }

        return true;
    }
}
