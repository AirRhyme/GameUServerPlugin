package dev.gameu.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ViewTrohpy implements CommandExecutor, Listener {
    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();
    public static Map<String, Integer> trophiesnumber = new HashMap<String, Integer>();
    public static List<Inventory> invs = new ArrayList<Inventory>();
    public Inventory inventory;

    public void setupInventory(Player owner){

        inventory = Bukkit.createInventory(owner, 45,  ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You are not a player and cannot view anything");
            return true;
        }
        Player p = (Player)sender;
        Inventory inv = Bukkit.createInventory(p, 45,  ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");
        invs.add(inv);
        if (menus.containsKey(p.getUniqueId().toString()))
            inv.setContents(menus.get(p.getUniqueId().toString()));

        p.openInventory(inv);
        return true;
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event){
       if(!invs.contains(event.getInventory())){
           return;
       }

       event.setCancelled(true);
       return;
    }

    public static void addTrophy(Player p, ItemStack trophy, Player sendr) {
        ItemStack[] content = new ItemStack[]{trophy}; // Create an array with the new trophy item

        // Retrieve the existing value from the HashMap
        ItemStack[] existingContent = menus.get(p.getUniqueId().toString());

        // Check if there is existing content in the HashMap for the player
        if (existingContent != null) {
            // Create a new array with a size that can accommodate both the existing content and the new trophy item
            ItemStack[] newContent = Arrays.copyOf(existingContent, existingContent.length + 1);

            // Append the new trophy item to the new array
            newContent[existingContent.length] = trophy;

            // Update the value in the HashMap with the new array
            menus.put(p.getUniqueId().toString(), newContent);
        } else {
            // If there is no existing content, simply add the new array to the HashMap
            menus.put(p.getUniqueId().toString(), content);
        }

        trophiesnumber.put(p.getUniqueId().toString(), trophiesnumber.get(p.getUniqueId().toString()) + 1);
        if(trophiesnumber.get(p.getUniqueId().toString()) == 38){
            System.out.println("New page");

        }
    }


    @EventHandler
    public void onGUIClosed(InventoryCloseEvent event){

    }

}
