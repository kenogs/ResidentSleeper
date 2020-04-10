package com.natured.residentsleeper;

import org.bukkit.plugin.java.JavaPlugin;

public class ResidentSleeper extends JavaPlugin {

    @Override
    public void onEnable(){

        PluginContext pluginContext = new PluginContext(this);

        BedListener bedListener = new BedListener(pluginContext);
        ResidentSleeperCommand residentSleeperCommand = new ResidentSleeperCommand(pluginContext);

        getServer().getPluginManager().registerEvents(bedListener, this);
        getCommand("residentsleeper").setExecutor(residentSleeperCommand);
    }
}