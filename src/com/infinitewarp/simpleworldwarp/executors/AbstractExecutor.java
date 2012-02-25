package com.infinitewarp.simpleworldwarp.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinitewarp.simpleworldwarp.StylizedMessager;

abstract public class AbstractExecutor implements CommandExecutor {

    private JavaPlugin plugin = null;
    private String permissionRoot = "";
    private StylizedMessager messager = null;

    public AbstractExecutor(JavaPlugin plugin, String permissionRoot, StylizedMessager messager) {
        this.plugin = plugin;
        this.permissionRoot = permissionRoot;
        this.messager = messager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!canExecuteCommand(sender, command)) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            return true;
        }
        return execute(sender, args);
    }

    abstract protected boolean execute(CommandSender sender, String[] args);

    protected JavaPlugin getPlugin() {
        return plugin;
    }

    protected boolean canExecuteCommand(CommandSender sender, Command command) {
        if ((sender instanceof Player)) {
            return ((Player) sender).hasPermission(permissionRoot + "." + command.getName());
        }
        return true;
    }

    protected boolean worldExists(String worldName) {
        return plugin.getServer().getWorld(worldName) != null;
    }

    protected StylizedMessager getMessager() {
        return messager;
    }
}
