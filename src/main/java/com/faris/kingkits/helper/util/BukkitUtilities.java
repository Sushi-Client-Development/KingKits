package com.faris.kingkits.helper.util;

import com.faris.kingkits.KingKits;
import com.faris.kingkits.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BukkitUtilities {

    private BukkitUtilities() {
    }

    public static void cancelTask(int taskID) {
        if (Bukkit.getServer().getScheduler().isCurrentlyRunning(taskID) || Bukkit.getServer().getScheduler().isQueued(taskID))
            Bukkit.getServer().getScheduler().cancelTask(taskID);
    }

    public static boolean hasPlugin(String pluginName) {
        return pluginName != null && Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName);
    }

    public static boolean performCommand(String command) {
        return command != null && Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

    public static void sendMessageSync(final CommandSender sender, final String message) {
        Bukkit.getServer().getScheduler().runTask(KingKits.getInstance(), () -> {
            if (!(sender instanceof Player) || ((Player) sender).isOnline()) {
                sender.sendMessage(ChatUtilities.replaceChatCodes(message));
            }
        });
    }

    public static void sendMessageSync(final CommandSender sender, final Messages message, final Object... format) {
        Bukkit.getServer().getScheduler().runTask(KingKits.getInstance(), () -> {
            if (!(sender instanceof Player) || ((Player) sender).isOnline()) {
                Messages.sendMessage(sender, message, format);
            }
        });
    }

}