package com.natured.residentsleeper;

import org.bukkit.plugin.java.JavaPlugin;

public class ResidentSleeper extends JavaPlugin {

    @Override
    public void onEnable(){

        getServer().getPluginManager().registerEvents(new BedListener(), this);
        getCommand("residentsleeper").setExecutor(new ResidentSleeperCommand());
    }
}
