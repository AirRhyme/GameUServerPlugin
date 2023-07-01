package dev.gameu.commands;

import dev.gameu.utils.DiscordWebhook;
import dev.gameu.utils.PosUtils;
import dev.gameu.utils.UrlUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;

public class eventCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("You are not player.");
        }

        Player p = (Player)sender;

        //Handle Create subcommand

        if(args.length == 0) {
            Location to = PosUtils.getEventLocation();
            if(to == null){
                p.sendMessage(ChatColor.DARK_RED + "There is no event currently!");
                return true;
            }
            p.teleport(to);
            p.sendMessage(ChatColor.GREEN + "Teleported you to the event.");
            return true;
        }

        else if(args[0].equals("create")){
            //p.sendMessage("1");
            if(p.hasPermission("GameU.admin")){

                PosUtils.setEventLocation(p.getLocation());

                p.sendMessage(ChatColor.GREEN + "Event Created!");
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "There is an event starting! Teleport to it with /event!");
                DiscordWebhook webhook = new DiscordWebhook(UrlUtils.testhook);
                webhook.setContent("Event");
                webhook.setAvatarUrl("https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128");
                webhook.setUsername("GameU's Plugin!");
                webhook.setTts(true);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Event In-Game")
                        .setDescription("Join up!")
                        .setColor(Color.RED)
                        .addField("There is an event starting", "Join in game and to /event", true)
                        .setThumbnail("https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128")
                        .setFooter("Event created by " + p.getName(), "https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128")
                        .setImage("https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128")
                        .setAuthor("Dev", "https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128", "https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128")
                        .setUrl("https://cdn.discordapp.com/icons/501461610364141568/97edc55ca7c5ced02a4dc79973eed7e5.webp?size=128"));
                try {
                    webhook.execute(); //Handle exception
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }else {
                p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "" + ChatColor.UNDERLINE + "You do not have permission node GameU.admin");
                return true;
            }
        }
        //Handle normal command


        else if(args[0].equals("delete")){
            //p.sendMessage("1");
            if(p.hasPermission("GameU.admin")) {
                PosUtils.setEventLocation(null);
                p.sendMessage(ChatColor.GREEN + "Deleted the event. Players will now recive a no event message!");
                return true;
            }else{
                p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "" + ChatColor.UNDERLINE + "You do not have permission node GameU.admin");
                return true;
            }
        }

        return true;
    }
}
