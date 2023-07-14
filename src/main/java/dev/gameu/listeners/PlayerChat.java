package dev.gameu.listeners;

import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void playerChatted(PlayerChatEvent event){
        if(event.getPlayer().isOp()){

        }else{
            event.setCancelled(MsgUtils.INSTANCE.getChatMuted());
            event.getPlayer().sendMessage(ChatColor.RED + "Your not lagging, silly. Chat's just muted right now!");
        }
    }

}
