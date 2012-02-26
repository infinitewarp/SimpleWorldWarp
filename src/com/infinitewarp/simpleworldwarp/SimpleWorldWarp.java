package com.infinitewarp.simpleworldwarp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinitewarp.simpleworldwarp.executors.CreateExecutor;
import com.infinitewarp.simpleworldwarp.executors.DeleteExecutor;
import com.infinitewarp.simpleworldwarp.executors.ListExecutor;
import com.infinitewarp.simpleworldwarp.executors.WarpExecutor;

public class SimpleWorldWarp extends JavaPlugin {
    private static final String PLUGIN_NAME = "SimpleWorldWarp";
    private static final String LOG_PREFIX = "[" + PLUGIN_NAME + "] ";
    private static final String PERMISSION_ROOT = PLUGIN_NAME.toLowerCase();

    private static final Logger log = Logger.getLogger("Minecraft");
    private static final StylizedMessager messager = new StylizedMessager(LOG_PREFIX);

    @Override
    public void onEnable() {
        initializeWorlds();
        getCommand("wwarp").setExecutor(new WarpExecutor(this, PERMISSION_ROOT, messager));
        getCommand("wcreate").setExecutor(new CreateExecutor(this, PERMISSION_ROOT, messager));
        getCommand("wdelete").setExecutor(new DeleteExecutor(this, PERMISSION_ROOT, messager));
        getCommand("wlist").setExecutor(new ListExecutor(this, PERMISSION_ROOT, messager));
        log.info(LOG_PREFIX + PLUGIN_NAME + " is now enabled.");
    }

    @Override
    public void onDisable() {
        log.info(LOG_PREFIX + PLUGIN_NAME + " is now disabled.");
    }

    private void initializeWorlds() {
        // Start up worlds that are saved in the config.
        Set<String> savedWorldNames = new HashSet<String>();
        try {
            savedWorldNames = getConfig().getConfigurationSection("worlds").getKeys(false);
            for (String worldName : savedWorldNames) {
                if (getServer().getWorld(worldName) == null) {
                    if (!startupWorldName(worldName)) {
                        log.warning(LOG_PREFIX + "Invalid config encountered for world '" + worldName
                                + "'. Removing world from config.");
                        getConfig().set("worlds." + worldName, null);
                    }
                } else {
                    // Upon success, remove this world from the config.
                    // Allow the runningWorlds loop to recreate it.
                    getConfig().set("worlds." + worldName, null);
                }
            }
        } catch (NullPointerException e) {
            // getKeys may throw NPE if config doesn't have a "worlds" section.
            log.warning(LOG_PREFIX + "Caught NPE while loading worlds from config");
            e.printStackTrace();
        }

        // Add now-running worlds to the config.
        List<World> runningWorlds = getServer().getWorlds();
        for (World world : runningWorlds) {
            String worldName = world.getName();
            log.info(LOG_PREFIX + "Adding world '" + worldName + "' to saved world list config.");
            getConfig().set("worlds." + worldName + ".seed", world.getSeed());
            getConfig().set("worlds." + worldName + ".environment", world.getEnvironment().toString());
        }

        saveConfig();
    }

    private boolean startupWorldName(String worldName) {
        String environmentName = getConfig().getString("worlds." + worldName + ".environment");

        Long seed = Long.valueOf(getConfig().getString("worlds." + worldName + ".seed"));
        if (seed == null || environmentName == null) {
            return false;
        }

        Environment environment = null;
        try {
            environment = Environment.valueOf(environmentName);
        } catch (IllegalArgumentException e) {
            return false;
        }

        log.info(LOG_PREFIX + "Attempting to start up world " + worldName);
        WorldCreator creator = new WorldCreator(worldName);
        creator.seed(seed);
        creator.environment(environment);
        getServer().createWorld(creator);

        return true;
    }
}
