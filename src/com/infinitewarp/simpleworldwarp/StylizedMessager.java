package com.infinitewarp.simpleworldwarp;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StylizedMessager {
    private String prefix = "";

    public StylizedMessager(String prefix) {
        this.prefix = prefix;
    }

    public void send(CommandSender sender, String message) {
        send(sender, ChatColor.WHITE, message);
    }

    public void send(CommandSender sender, ChatColor color, String message) {
        String output = "";
        if (sender instanceof Player) {
            output = color + message;
        } else {
            output = prefix + message;
        }
        sender.sendMessage(output);
    }
}
