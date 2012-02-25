package com.infinitewarp.simpleworldwarp.executors;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinitewarp.simpleworldwarp.StylizedMessager;

public class WarpExecutor extends AbstractExecutor {

    public WarpExecutor(JavaPlugin plugin, String permissionRoot, StylizedMessager messager) {
        super(plugin, permissionRoot, messager);
    }

    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return false;
        }
        if (!(sender instanceof Player)) {
            getMessager().send(sender, "You must be in-game to use this command.");
            return true;
        }

        String worldName = args[0];

        if (!worldExists(worldName)) {
            getMessager().send(sender, "World '" + worldName + "' does not exist.");
            return true;
        }

        Player player = (Player) sender;
        Location location = getPlugin().getServer().getWorld(worldName).getSpawnLocation();
        player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
        return true;
    }
}
