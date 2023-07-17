package dev.gameu.commands;

import dev.gameu.utils.CommandUtils;
import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QoLToggle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("gameu.admin")) {
            CommandUtils.setQualityOfLifeCommandsEnabled(!CommandUtils.isQualityOfLifeCommandsEnabled());
            commandSender.sendMessage(ChatColor.GREEN + "Set the quality of life commands feature to " + CommandUtils.isQualityOfLifeCommandsEnabled());
            return true;
        } else {
            MsgUtils.INSTANCE.sendPermissionError("gameu.admin", commandSender);
            return true;
        }
    }
}
