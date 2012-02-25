package com.infinitewarp.simpleworldwarp.executors;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinitewarp.simpleworldwarp.StylizedMessager;

public class ListExecutor extends AbstractExecutor {

    public ListExecutor(JavaPlugin plugin, String permissionRoot, StylizedMessager messager) {
        super(plugin, permissionRoot, messager);
    }

    protected boolean execute(CommandSender sender, String[] args) {
        getMessager().send(sender, "Current world list:");
        for (World world : getPlugin().getServer().getWorlds()) {
            ChatColor color = ChatColor.GRAY;
            if (world.getEnvironment().compareTo(Environment.NORMAL) == 0) {
                color = ChatColor.GREEN;
            } else if (world.getEnvironment().compareTo(Environment.NETHER) == 0) {
                color = ChatColor.RED;
            } else if (world.getEnvironment().compareTo(Environment.THE_END) == 0) {
                color = ChatColor.AQUA;
            }
            sender.sendMessage(color + world.getName() + " (" + world.getEnvironment() + ")");
        }
        return true;
    }
}
