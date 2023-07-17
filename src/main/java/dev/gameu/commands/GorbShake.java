package dev.gameu.commands;

import dev.gameu.utils.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GorbShake implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ItemStack gorbShake = new ItemStack(Material.MILK_BUCKET);
        ItemMeta meta = gorbShake.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Gorb Shake");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "The Gorb Shake");
        lore.add("");
        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Only the worthy are able to survive this drink");
        lore.add(ChatColor.DARK_GRAY + "Are you worthy?");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        gorbShake.setItemMeta(meta);

        if (!(commandSender instanceof Player)) {
            MsgUtils.INSTANCE.sendPermissionError("that's not a valid node... You need to be a player", commandSender);
            return true;
        }

        Player p = (Player) commandSender;
        p.getInventory().addItem(gorbShake);

        return true;
    }

}
