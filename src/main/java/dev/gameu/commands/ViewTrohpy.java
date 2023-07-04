package dev.gameu.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ViewTrohpy implements CommandExecutor, Listener {
    public static Map<String, ItemStack[]> menus = (Map)new HashMap<>();

    public Inventory inventory;

    public void setupInventory(Player owner) {
        this.inventory = Bukkit.createInventory(owner, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You are not a player and cannot view anything");
            return true;
        }
        Player p = (Player)sender;
        Inventory inv = Bukkit.createInventory(p, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");
        if (menus.containsKey(p.getUniqueId().toString()))
            inv.setContents(menus.get(p.getUniqueId().toString()));
        p.openInventory(inv);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) {
            return; // Ignore if the clicked inventory is null
        }

        InventoryHolder holder = clickedInventory.getHolder();
        if (holder instanceof Player && event.getView().getTitle().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies")) {
            ItemStack currentItem = event.getCurrentItem();
            if (currentItem != null) {
                event.setCancelled(true); // Cancel the event to prevent taking items
                event.getWhoClicked().sendMessage("You cannot take items from this GUI.");
            }
        }
    }

    public static void addTrophy(Player p, ItemStack trophy, Player sendr) {
        trophy.setAmount(1);
        ItemMeta meta = trophy.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        trophy.setItemMeta(meta);
        ItemStack[] content = { trophy };
        ItemStack[] existingContent = menus.get(p.getUniqueId().toString());
        if (existingContent != null) {
            ItemStack[] newContent = Arrays.<ItemStack>copyOf(existingContent, existingContent.length + 1);
            newContent[existingContent.length] = trophy;
            menus.put(p.getUniqueId().toString(), newContent);
        } else {
            menus.put(p.getUniqueId().toString(), content);
        }
        //p.getInventory().addItem(trophy);
        p.sendMessage(ChatColor.GREEN + "You have been awared a trophy!");
    }

    @EventHandler
    public void onGUIClosed(InventoryCloseEvent event) {}
}
