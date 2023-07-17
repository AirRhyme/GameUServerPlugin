package dev.gameu.commands;

import dev.gameu.utils.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ViewTrohpy implements CommandExecutor, Listener {
    public static Map<String, ItemStack[]> menus = (Map) new HashMap<>();
    private final String menuTitle = "Menu";
    private final int maxPages = 5;
    private final int slotsPerPage = 45;
    private final ItemStack nextPageItem;
    private final ItemStack previousPageItem;
    public Inventory inventory;

    public void setupInventory(Player owner) {
        this.inventory = Bukkit.createInventory(owner, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");
    }

    public ViewTrohpy() {
        nextPageItem = createNavigationItem("Next Page", Material.ARROW);
        previousPageItem = createNavigationItem("Previous Page", Material.ARROW);
    }

    public static void addTrophy(Player p, ItemStack trophy, Player sendr) {
        trophy.setAmount(1);
        ItemMeta meta = trophy.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        trophy.setItemMeta(meta);
        ItemStack[] content = {trophy};
        ItemStack[] existingContent = ViewTrohpy.menus.get(p.getUniqueId().toString());
        if (existingContent != null) {
            ItemStack[] newContent = Arrays.<ItemStack>copyOf(existingContent, existingContent.length + 1);
            newContent[existingContent.length] = trophy;
            ViewTrohpy.menus.put(p.getUniqueId().toString(), newContent);
        } else {
            ViewTrohpy.menus.put(p.getUniqueId().toString(), content);
        }
        //p.getInventory().addItem(trophy);
        p.sendMessage(ChatColor.GREEN + "You have been awared a trophy!");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You are not a player and cannot view anything");
            return true;
        }
        Player p = (Player) sender;
        Inventory inv = Bukkit.createInventory(p, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Trophies");
        if (menus.containsKey(p.getUniqueId().toString()))
            inv.setContents(menus.get(p.getUniqueId().toString()));

        //p.openInventory(inv);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof MenuHolder) {
            event.setCancelled(true);
        }
    }

    private ItemStack createNavigationItem(String displayName, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onGUIClosed(InventoryCloseEvent event) {}
}
