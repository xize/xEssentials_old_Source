package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdcheck {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("check")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.check")) {
					if(args.length == 0) {
						if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
							String date = fileManager.getStringValue(sender.getName() + ".yml", "submitDate", fileManager.getDir() + File.separator + "modreq");
							String message = fileManager.getStringValue(sender.getName() + ".yml", "message", fileManager.getDir() + File.separator + "modreq");
							sender.sendMessage(ChatColor.GOLD + ".oO___[your modreqs]___Oo.");
							sender.sendMessage(ChatColor.GOLD + "[" + date + "]" + ChatColor.RED + message + ChatColor.GRAY + "by: " + sender.getName());
						} else {
							sender.sendMessage(ChatColor.RED + "you do not own any modreqs!");
						}
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[modreq check help]___Oo.");
							sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/check " + ChatColor.WHITE + ": checks your own modreqs");
							if(sender.hasPermission("xEssentials.command.check.admin")) {
								sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/check list " + ChatColor.WHITE + ": shows a list of currently open modreqs");
								sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/check <player> " + ChatColor.WHITE + ": checks the players personal ticket");
							}
						} else if(args[0].equalsIgnoreCase("list")) {
							if(sender.hasPermission("xEssentials.command.check.admin")) {
								File[] files = fileManager.getFileList(fileManager.getDir() + File.separator + "modreq");
								for(File f : files) {
									String date = fileManager.getStringValue(f.getName(), "submitDate", fileManager.getDir() + File.separator + "modreq");
									String message = fileManager.getStringValue(f.getName() + ".yml", "message", fileManager.getDir() + File.separator + "modreq");
									sender.sendMessage(ChatColor.GOLD + ".oO___[modreq list]___Oo.");
									sender.sendMessage(ChatColor.GOLD + "[" + date + "]" + ChatColor.RED + message + ChatColor.GRAY + "by: " + f.getName().replace(".yml", ""));
								}
							} else {
								playerPermission.getPermissionError(sender, cmd, args);
							}
						} else {
							if(sender.hasPermission("xEssentials.command.check.admin")) {
								Player p = Bukkit.getPlayer(args[0]);
								if(p instanceof Player) {
									if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
										String message = fileManager.getStringValue(p.getName() + ".yml", "message", fileManager.getDir() + File.separator + "modreq");
										String date = fileManager.getStringValue(p.getName() + ".yml", "submitDate", fileManager.getDir() + File.separator + "modreq");
										sender.sendMessage(ChatColor.GOLD + ".oO___[modreq from player " + p.getName() + "]___Oo.");
										sender.sendMessage(ChatColor.GOLD + "[" + date + "]" + ChatColor.RED + message + ChatColor.GRAY + "by: " + p.getName());
									} else {
										sender.sendMessage(ChatColor.RED + "modreq for player " + p.getName() + " does not exist");
									}
								} else {
									if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "modreq")) {
										String message = fileManager.getStringValue(args[0] + ".yml", "message", fileManager.getDir() + File.separator + "modreq");
										String date = fileManager.getStringValue(args[0] + ".yml", "submitDate", fileManager.getDir() + File.separator + "modreq");
										sender.sendMessage(ChatColor.GOLD + ".oO___[modreq from player " + args[0] + "]___Oo.");
										sender.sendMessage(ChatColor.GOLD + "[" + date + "]" + ChatColor.RED + message + ChatColor.GRAY + "by: " + args[0]);
									} else {
										sender.sendMessage(ChatColor.RED + "modreq for player " + args[0] + " does not exist");
									}
								}
							} else {
								playerPermission.getPermissionError(sender, cmd, args);
							}
						}
					}
				} else {
					playerPermission.getPermissionError(sender, cmd, args);
				}
			} else {
				consolePermission.getConsoleMessage(sender);
			}
		}
		return false;
	}

}
