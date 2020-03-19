package com.natured.residentsleeper;

import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;

public class ResidentSleeperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player sender = null;
        if (commandSender instanceof Player)
            sender = (Player) commandSender;

        if (sender == null) {

            error("The command /residentsleeper cannot be executed by a non-player.");
            return true;
        }

        if (!sender.hasPermission("residentsleeper.use")) {

            sendErrorMessage(sender, "You don't have enough permission.");
            return true;
        }

        Collection<Player> players = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
        for (Iterator<Player> playerIterator = players.iterator(); playerIterator.hasNext();) {

            Player player = playerIterator.next();

            if (player.isSleeping()) {

                player.wakeup( false);
                sendCanceledMessage(sender, player);
                break;
            }
        }

        return true;
    }

    private void sendCanceledMessage(Player sender, Player target) {

        ComponentBuilder componentBuilder = new ComponentBuilder();
        componentBuilder.append(sender.getDisplayName());
        componentBuilder.color(net.md_5.bungee.api.ChatColor.DARK_PURPLE);
        componentBuilder.append(" doesn't want ");
        componentBuilder.append(target.getDisplayName());
        componentBuilder.color(net.md_5.bungee.api.ChatColor.DARK_PURPLE);
        componentBuilder.append(" to rest.");

        Bukkit.getServer().spigot().broadcast(componentBuilder.create());
    }

    private void sendErrorMessage(Player player, String message) {

        player.sendMessage(ChatColor.DARK_RED + message);
    }

    private void error(String message) {

        Bukkit.getLogger().severe("[ResidentSleeper] " + message);
    }
}
