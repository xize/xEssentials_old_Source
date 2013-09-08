package tv.mineinthebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.events.handler;
import tv.mineinthebox.permissions.playerPermission;

public class cmdxessentials {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("xessentials")) {
			if(sender.hasPermission("xEssentials.command.xessentials")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[" + xEssentials.getPlugin().getName() + xEssentials.getPlugin().getDescription().getVersion() + "]___Oo.");
					sender.sendMessage(ChatColor.GRAY + "coded by Xeph0re aka Xize");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						if(sender.hasPermission("xEssentials.command.reload")) {
							sender.sendMessage(ChatColor.GREEN + "reloading " + xEssentials.getPlugin().getName() + xEssentials.getPlugin().getDescription().getVersion());
							handler.restartListeners();
							sender.sendMessage(ChatColor.GRAY + "reload for " + ChatColor.GREEN + " xEssentials " + ChatColor.GRAY + " is successfully done");
							sender.sendMessage(ChatColor.GRAY + "memory has been ejected and injected with updated configuration values");
							sender.sendMessage(ChatColor.GRAY + "all listeners are reactivated!");
						} else {
							playerPermission.getPermissionError(sender, cmd, args);
						}
					} else if(args[0].equalsIgnoreCase("version")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[" + xEssentials.getPlugin().getName() + xEssentials.getPlugin().getDescription().getVersion() + "]___Oo.");
						sender.sendMessage(ChatColor.GRAY + "coded by Xeph0re aka Xize");
					} else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[" + xEssentials.getPlugin().getName() + xEssentials.getPlugin().getDescription().getVersion() + "]___Oo.");
						sender.sendMessage(ChatColor.GRAY + "coded by Xeph0re aka Xize");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/xEssentials version " + ChatColor.WHITE + ": shows version of xEssentials");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/xEssentials reload " + ChatColor.WHITE + ": reload xEssentials");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
