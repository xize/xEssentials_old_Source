package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.permissions.playerPermission;

public class cmdunmute {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("unmute")) {
			if(sender.hasPermission("xEssentials.command.unmute")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[unmute help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/unmute <player>");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[unmute help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/unmute <player>");
					} else {
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							if(xEssentialsMemory.returnPlayer(victem).containsKey("muted")) {
								xEssentialsMemory.returnPlayer(victem).put("muted", null);
								xEssentialsMemory.updatePlayerConfig(victem);
								victem.sendMessage(ChatColor.GREEN + "you are unmuted by " + sender.getName());
								sender.sendMessage(ChatColor.GREEN +  "you have unmuted " + sender.getName());
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is not muted!");
							}
						} else {
							if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "players")) {
								if(fileManager.isSet(args[0] + ".yml", "muted", fileManager.getDir() + File.separator + "players")) {
									sender.sendMessage(ChatColor.GREEN + "successfully unmuted offline player " + args[0]);
									fileManager.removeNode(args[0] + ".yml", "muted", fileManager.getDir() + File.separator + "players");
								} else {
									sender.sendMessage(ChatColor.RED + args[0] + " is not muted!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " has not played before!");
							}
						}
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
