package dev.gameu.commands;

import dev.gameu.utils.CommandUtils;
import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("gameu.admin")) {
            if (CommandUtils.isQualityOfLifeCommandsEnabled()) {
                boolean newChatMutedValue = MsgUtils.INSTANCE.toggleChatMuted();
                commandSender.sendMessage(ChatColor.GREEN + "Success! Chat muting is now set to " + newChatMutedValue);
                return true;
            } else {
                //TODO: Handle the disabling of the QoL Commands
                MsgUtils.INSTANCE.sendNotEnabledError("mutechat", commandSender);
                return true;
            }
        }else{
            MsgUtils.INSTANCE.sendPermissionError("gameu.admin", commandSender);
            return true;
        }
    }
}
