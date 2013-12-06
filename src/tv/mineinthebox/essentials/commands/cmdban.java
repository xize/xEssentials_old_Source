package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.bansystem.ban;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

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
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Reason", "the ban hammer has spoken!", fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								showBanResults(victem.getName());
								victem.kickPlayer(fileManager.getStringValue(victem.getName() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
							} else {
								String ip = fileManager.getStringValue(args[0].toLowerCase() + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Reason", "the ban hammer has spoken!", fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								showBanResults(args[0].toLowerCase());
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
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Reason", build.toString().replace("-hide", ""), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								victem.kickPlayer(fileManager.getStringValue(victem.getName().toLowerCase() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						} else {
							if(!ban.isBanned(args[0])) {
								String ip = fileManager.getStringValue(args[0].toLowerCase() + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Reason", build.toString().replace("-hide", ""), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						}
					} else {
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							if(!ban.isBanned(victem)) {
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Reason", build.toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
								showBanResults(victem.getName());
								victem.kickPlayer(fileManager.getStringValue(victem.getName().toLowerCase() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
							}
						} else {
							if(!ban.isBanned(args[0])) {
								String ip = fileManager.getStringValue(args[0].toLowerCase() + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Reason", build.toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
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
		String player = fileManager.getStringValue(playerName.toLowerCase() + ".yml", "Player", fileManager.getDir() + File.separator + "bans");
		String BannedOn = fileManager.getStringValue(playerName.toLowerCase() + ".yml", "BannedOn", fileManager.getDir() + File.separator + "bans");
		String BannedBy = fileManager.getStringValue(playerName.toLowerCase() + ".yml", "BannedBy", fileManager.getDir() + File.separator + "bans");
		String Reason = fileManager.getStringValue(playerName.toLowerCase() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans");
		Boolean Tempbanned = fileManager.getBooleanValue(playerName.toLowerCase() + ".yml", "Tempbanned", fileManager.getDir() + File.separator + "bans");
		Boolean Banned = fileManager.getBooleanValue(playerName.toLowerCase() + ".yml", "Banned", fileManager.getDir() + File.separator + "bans");
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
	
	public static void setBanned(String ban_message, Player p, banType type) {
		if(type == banType.floodspam) {
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Player", p.getName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "ip", p.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
			p.kickPlayer(xEssentialsMemory.antiFloodSpamMessage);
			showBanResults(p.getName());	
		} else if(type == banType.humanspam) {
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Player", p.getName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "ip", p.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
			p.kickPlayer(xEssentialsMemory.humanSpamMessage);
		} else if(type == banType.pwnage) {
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Player", p.getName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "ip", p.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.getName().toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
			p.kickPlayer(xEssentialsMemory.pwnageMessage);
		}
	}
	
	public static void setBanned(String ban_message, String p, banType type) {
		if(type == banType.floodspam) {
			fileManager.writeFile(p.toLowerCase() + ".yml", "Player", p, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "ip", "unknown", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
			showBanResults(p);	
		} else if(type == banType.humanspam) {
			fileManager.writeFile(p.toLowerCase() + ".yml", "Player", p, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "ip", "unknown", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
		} else if(type == banType.pwnage) {
			fileManager.writeFile(p.toLowerCase() + ".yml", "Player", p, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "ip", "unknown", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "BannedBy", "[" + type.name() + "]", fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Reason", ban_message, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
			fileManager.writeFile(p.toLowerCase() + ".yml", "Banned", true, fileManager.getDir() + File.separator + "bans");
		}
	}
	
	public static enum banType {
		floodspam,
		humanspam,
		pwnage
	}

}
