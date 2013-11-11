package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.bansystem.ban;

public class cmdunban {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("unban")) {
			if(sender.hasPermission("xEssentials.command.unban")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[unban]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/unban <playername>" + ChatColor.WHITE + " : unbans a banned player");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[unban]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/unban <playername>" + ChatColor.WHITE + " : unbans a banned player");	
					} else {
						if(ban.isBanned(args[0])) {
							if(fileManager.getBooleanValue(args[0] + ".yml", "Banned", fileManager.getDir() + File.separator + "bans")) {
								fileManager.writeFile(args[0] + ".yml", "Banned", false, fileManager.getDir() + File.separator + "bans");
								sender.sendMessage(ChatColor.GREEN + args[0] + " is no longer banned!");
							} else if(fileManager.getBooleanValue(args[0] + ".yml", "Tempbanned", fileManager.getDir() + File.separator + "bans")) {
								fileManager.writeFile(args[0] + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								sender.sendMessage(ChatColor.GREEN + args[0] + " is no longer tempory banned!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + args[0] + " seems not to be banned!");
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
