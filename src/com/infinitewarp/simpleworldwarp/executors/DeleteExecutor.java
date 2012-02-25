package com.infinitewarp.simpleworldwarp.executors;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinitewarp.simpleworldwarp.StylizedMessager;

public class DeleteExecutor extends AbstractExecutor {

    public DeleteExecutor(JavaPlugin plugin, String permissionRoot, StylizedMessager messager) {
        super(plugin, permissionRoot, messager);
    }

    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return false;
        }

        String worldName = args[0];
        if (!worldExists(worldName)) {
            getMessager().send(sender, "World '" + worldName + "' does not exist!");
            return true;
        }
        if (getPlugin().getServer().getWorld(worldName).getPlayers().size() > 0) {
            getMessager().send(sender, "World '" + worldName + "' contains active players!");
            return true;
        }

        getPlugin().getServer().unloadWorld(worldName, true);
        getMessager().send(sender, "World '" + worldName + "' successfully unloaded!");
        sender.sendMessage("Remember to manually delete world folder.");

        return true;
    }
}
