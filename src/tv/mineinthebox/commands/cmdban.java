package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.playerPermission;
import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.timeunit.timeunits;

public class cmdban {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ban")) {
			if(sender.hasPermission("xEssentials.command.ban")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[ban management]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/ban <player> <description>" + ChatColor.WHITE + " : bans a player with a given description");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[ban management]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/ban <player> <description>" + ChatColor.WHITE + " : bans a player with a given description");
					} else {
						Player victem = Bukkit.getPlayer(args[0]);
						if(!ban.isBanned(args[0])) {
							if(victem instanceof Player) {
								fileManager.writeFile(victem.getName() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Reason", "the ban hammer has spoken!", fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								victem.kickPlayer(fileManager.getStringValue(victem.getName() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
								showBanResults(victem.getName());
							} else {
								String ip = fileManager.getStringValue(args[0] + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0] + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Reason", "the ban hammer has spoken!", fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								showBanResults(args[0]);
							}
						} else {
							sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
						}
					}
				} else if(args.length > 1) {
					StringBuilder build = new StringBuilder();
					for(int i = 1; i < args.length; i++) {
						build.append(args[i] + " ");
					}
					if(build.toString().contains("-hide")) {
						//is used for spambots so we can hide the ban results as it will continues spam.
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							if(!ban.isBanned(victem)) {
								fileManager.writeFile(victem.getName() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Reason", build.toString().replace("-hide", ""), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								victem.kickPlayer(fileManager.getStringValue(victem.getName() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						} else {
							if(!ban.isBanned(args[0])) {
								String ip = fileManager.getStringValue(args[0] + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0] + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Reason", build.toString().replace("-hide", ""), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						}
					} else {
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							if(!ban.isBanned(victem)) {
								fileManager.writeFile(victem.getName() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Reason", build.toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								victem.kickPlayer(fileManager.getStringValue(victem.getName() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
								showBanResults(victem.getName());
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						} else {
							if(!ban.isBanned(args[0])) {
								String ip = fileManager.getStringValue(args[0] + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0] + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Reason", build.toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0] + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								showBanResults(args[0]);
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
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

	public static void showBanResults(String playerName) {
		String player = fileManager.getStringValue(playerName + ".yml", "Player", fileManager.getDir() + File.separator + "bans");
		String BannedOn = fileManager.getStringValue(playerName + ".yml", "BannedOn", fileManager.getDir() + File.separator + "bans");
		String BannedBy = fileManager.getStringValue(playerName + ".yml", "BannedBy", fileManager.getDir() + File.separator + "bans");
		String Reason = fileManager.getStringValue(playerName + ".yml", "Reason", fileManager.getDir() + File.separator + "bans");
		Boolean Tempbanned = fileManager.getBooleanValue(playerName + ".yml", "Tempbanned", fileManager.getDir() + File.separator + "bans");
		Boolean Banned = fileManager.getBooleanValue(playerName + ".yml", "Banned", fileManager.getDir() + File.separator + "bans");
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.GOLD + ".oO___[Banned]___Oo.");
			p.sendMessage(ChatColor.GRAY + "player: " + player);
			p.sendMessage(ChatColor.GRAY + "banned on: " + BannedOn);
			p.sendMessage(ChatColor.GRAY + "banned by: " + BannedBy);
			p.sendMessage(ChatColor.GRAY + "reason: " + Reason);
			p.sendMessage(ChatColor.GRAY + "tempbanned: " + Tempbanned);
			p.sendMessage(ChatColor.GRAY + "banned: " + Banned);
		}
	}

}
