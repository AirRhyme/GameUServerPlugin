package dev.gameu.listeners;

import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void playerChatted(PlayerChatEvent event){
        if (event.getPlayer().hasPermission("gameu.bypasschat")) {

        } else {
            if (MsgUtils.INSTANCE.getChatMuted()) {
                event.setCancelled(MsgUtils.INSTANCE.getChatMuted());
                event.getPlayer().sendMessage(ChatColor.RED + "Chat is currently muted.");
            }
        }
    }

}
