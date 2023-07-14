package dev.gameu.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgUtils {
    public static MsgUtils INSTANCE = new MsgUtils();
    public boolean chatmuted;
    public void sendPermissionError(String permissionMissing, CommandSender playerToSend){
        playerToSend.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "You do not have the required permissions to do this! Required Permission: " + permissionMissing);
    }

    public void setChatMuted(boolean newValue){
        chatmuted = newValue;
    }

    public boolean toggleChatMuted(){
        return chatmuted = !chatmuted;
    }

    public boolean getChatMuted(){
        return chatmuted;
    }


}
