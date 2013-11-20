package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

public class cmdmodreq {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("modreq")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.modreq")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[modreq]___Oo.");
						sender.sendMessage(ChatColor.DARK_GRAY + "player: " + ChatColor.GRAY + "/modreq" + ChatColor.GRAY + " : creates a ticket or modify the current one");
						sender.sendMessage(ChatColor.DARK_GRAY + "player: " + ChatColor.GRAY + "/check" + ChatColor.GRAY + " : checks your ticket status");
						if(sender.hasPermission("xEssentials.isStaff")) {
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/check <player>" + ChatColor.GRAY + " : checks players ticket status");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/claim <player>" + ChatColor.GRAY + " : claims the ticket of the player");
							sender.sendMessage(ChatColor.RED + "Admin:" + ChatColor.GRAY + "/done <message>" + ChatColor.GRAY + " : done a ticket, with a specific message");
						}
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[modreq]___Oo.");
							sender.sendMessage(ChatColor.DARK_GRAY + "player: " + ChatColor.GRAY + "/modreq" + ChatColor.GRAY + " : creates a ticket or modify the current one");
							sender.sendMessage(ChatColor.DARK_GRAY + "player: " + ChatColor.GRAY + "/check" + ChatColor.GRAY + " : checks your ticket status");
							if(sender.hasPermission("xEssentials.isStaff")) {
								sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/check <player>" + ChatColor.GRAY + " : checks players ticket status");
								sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/claim <player>" + ChatColor.GRAY + " : claims the ticket of the player");
								sender.sendMessage(ChatColor.RED + "Admin:" + ChatColor.GRAY + "/done <message>" + ChatColor.GRAY + " : done a ticket, with a specific message");
							}
						} else {
							if(fileManager.file_exists(sender.getName().toLowerCase() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
								StringBuilder message = new StringBuilder();
								for(int i = 0; i < args.length; i++) {
									message.append(args[i] + " ").toString();
								}
								Date date = timeunits.setLongToDate(System.currentTimeMillis());
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "submitDate", date.toString(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "message", message.toString(), fileManager.getDir() + File.separator + "modreq");
								Player p = (Player) sender;
								if(p instanceof Player) {
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "modreq");
								}
								sender.sendMessage(ChatColor.GREEN + "successfully updated old modreq!");
								Bukkit.broadcast(sender.getName() + " has updated his ticket, use /check " + sender.getName() + " to look into his ticket request", "xEssentials.isStaff");
							} else {
								StringBuilder message = new StringBuilder();
								for(int i = 0; i < args.length; i++) {
									message.append(args[i] + " ").toString();
								}
								Date date = timeunits.setLongToDate(System.currentTimeMillis());
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "submitDate", date.toString(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "message", message.toString(), fileManager.getDir() + File.separator + "modreq");
								Player p = (Player) sender;
								if(p instanceof Player) {
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "modreq");
									fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "modreq");
								}
								sender.sendMessage(ChatColor.GREEN + "successfully created a modreq!");
								Bukkit.broadcast(ChatColor.GREEN + sender.getName() + " has created a new modreq ticket, use /check " + sender.getName() + " to look into his ticket request", "xEssentials.isStaff");
							}
						}
					} else {
						if(fileManager.file_exists(sender.getName().toLowerCase() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
							StringBuilder message = new StringBuilder();
							for(int i = 0; i < args.length; i++) {
								message.append(args[i] + " ").toString();
							}
							Date date = timeunits.setLongToDate(System.currentTimeMillis());
							fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "submitDate", date.toString(), fileManager.getDir() + File.separator + "modreq");
							fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "message", message.toString(), fileManager.getDir() + File.separator + "modreq");
							Player p = (Player) sender;
							if(p instanceof Player) {
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "modreq");
							}
							sender.sendMessage(ChatColor.GREEN + "successfully updated old modreq!");
							Bukkit.broadcast(ChatColor.GREEN + sender.getName() + " has updated his ticket, use /check " + sender.getName() + " to look into his ticket request", "xEssentials.isStaff");
						} else {
							StringBuilder message = new StringBuilder();
							for(int i = 0; i < args.length; i++) {
								message.append(args[i] + " ").toString();
							}
							Date date = timeunits.setLongToDate(System.currentTimeMillis());
							fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "submitDate", date.toString(), fileManager.getDir() + File.separator + "modreq");
							fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "message", message.toString(), fileManager.getDir() + File.separator + "modreq");
							Player p = (Player) sender;
							if(p instanceof Player) {
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "modreq");
								fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "modreq");
							}
							sender.sendMessage(ChatColor.GREEN + "successfully created a modreq!");
							Bukkit.broadcast(ChatColor.GREEN + sender.getName() + " has created a new modreq ticket, use /check " + sender.getName() + " to look into his ticket request", "xEssentials.isStaff");
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
