package com.infinitewarp.simpleworldwarp;

import java.util.logging.Logger;

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
}
