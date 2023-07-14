package dev.gameu.commands;

import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("gameu.admin")){
            boolean newChatMutedValue = MsgUtils.INSTANCE.toggleChatMuted();
            commandSender.sendMessage(ChatColor.GREEN + "Success! Chat muting is now set to " + newChatMutedValue);
            return true;
        }else{
            MsgUtils.INSTANCE.sendPermissionError("gameu.admin", commandSender);
            return true;
        }
    }
}
