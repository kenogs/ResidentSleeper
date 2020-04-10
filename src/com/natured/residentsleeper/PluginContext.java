package com.natured.residentsleeper;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class PluginContext {

    private Plugin plugin;
    private BukkitTask bukkitTask;

    public PluginContext(Plugin plugin) {

        this.plugin = plugin;
    }

    public World getWorld() {
        return this.plugin.getServer().getWorld("world");
    }

    public Server getServer() { return this.plugin.getServer(); }

    public void queueTask(Runnable runnable, long delay) {

        if (this.bukkitTask != null) return;

        this.bukkitTask = Bukkit.getScheduler().runTaskLater(this.plugin, () -> {

            runnable.run();
            this.bukkitTask = null;

        }, delay);
    }

    public boolean cancelTask() {

        if (this.bukkitTask == null)
            return false;

        this.bukkitTask.cancel();
        boolean isCancelled = this.bukkitTask.isCancelled();
        this.bukkitTask = null;
        return isCancelled;
    }
}
