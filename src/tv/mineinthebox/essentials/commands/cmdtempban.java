package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.sql.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.bansystem.ban;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

public class cmdtempban {
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tempban")) {
			if(sender.hasPermission("xEssentials.command.tempban")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[tempban]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tempban <player> 4D <D,M,Y,h,m,s");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[tempban]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tempban <player> 4D <D,M,Y,h,m,s");
					} else {
						if(!ban.isTempBanned(args[0]) || !ban.isBanned(args[0])) {
							Player victem = Bukkit.getPlayer(args[0]);
							if(victem instanceof Player) {
								Date date = new Date(System.currentTimeMillis());
								//date.setDate(date.getDay() + 1);
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Reason", "Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(date.getTime()), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "time", date.getTime(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Tempbanned", true, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Banned", false, fileManager.getDir() + File.separator + "bans");
								cmdban.showBanResults(victem.getName());
								victem.kickPlayer("Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(date.getTime()));
							} else {
								Date date = new Date(System.currentTimeMillis());
								//date.setDate(date.getDay() + 1);
								String ip = fileManager.getStringValue(args[0] + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Reason", "Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(date.getTime()), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "time", date.getTime(), fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Tempbanned", true, fileManager.getDir() + File.separator + "bans");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "Banned", false, fileManager.getDir() + File.separator + "bans");
								cmdban.showBanResults(args[0]);
							}
						} else {
							sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
						}
					}
				} else if(args.length > 1) {
					if(!ban.isTempBanned(args[0]) || !ban.isBanned(args[0])) {
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							Long time = timeunits.convertDateArguments(args, sender);
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Player", victem.getName(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "ip", victem.getAddress().getHostName(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Reason", "Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(time), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "time", time, fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Tempbanned", true, fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(victem.getName().toLowerCase() + ".yml", "Banned", false, fileManager.getDir() + File.separator + "bans");
							cmdban.showBanResults(victem.getName());
							victem.kickPlayer("Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(time));
						} else {
							Long time = timeunits.convertDateArguments(args, sender);
							String ip = fileManager.getStringValue(args[0] + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "Player", args[0], fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedOn", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "BannedBy", sender.getName(), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "Reason", "Banned by " + sender.getName() + " banned for " + timeunits.getElapsedTime(time), fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "time", time, fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "Tempbanned", true, fileManager.getDir() + File.separator + "bans");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "Banned", false, fileManager.getDir() + File.separator + "bans");
							cmdban.showBanResults(args[0].toLowerCase());
						}
					} else {
						sender.sendMessage(ChatColor.RED + args[0] + " is allready banned!");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
