package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

public class cmddone {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("done")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.done")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[modreq done]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/done <player> " + ChatColor.WHITE + ": close a modreq of a player");
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[modreq done]___Oo.");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/done <player> " + ChatColor.WHITE + ": close a modreq of a player");
						} else {
							Player p = Bukkit.getPlayer(args[0]);
							if(p instanceof Player) {
								if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
									File f = fileManager.returnFile(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq");
									p.sendMessage(ChatColor.GREEN + sender.getName() + " has done your modreq!");
									p.sendMessage(ChatColor.GREEN + "comment: none");
									f.delete();
									for(Player player : Bukkit.getOnlinePlayers()) {
										if(player.hasPermission("xEssentials.command.done")) {
											if(!sender.getName().equalsIgnoreCase(player.getName())) {
												player.sendMessage(ChatColor.GREEN + sender.getName() + " has done " + args[0] + " modreq!");
												player.sendMessage(ChatColor.GREEN + "comment: none");
											}
										}
									}
									sender.sendMessage(ChatColor.GREEN + "successly done the modreq of player " + p.getName());
								} else {
									sender.sendMessage(ChatColor.RED + p.getName() + " hasn't any modreqs open");
								}
							} else {
								if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "modreq")) {
									File f = fileManager.returnFile(args[0] + ".yml", fileManager.getDir() + File.separator + "modreq");
									for(Player player : Bukkit.getOnlinePlayers()) {
										if(player.hasPermission("xEssentials.command.done")) {
											if(!sender.getName().equalsIgnoreCase(player.getName())) {
												player.sendMessage(ChatColor.GREEN + sender.getName() + " has done " + args[0] + " modreq!, but the player is offline saving");
												player.sendMessage(ChatColor.GREEN + "comment: none");
											}
										}
									}
									f.delete();
									sender.sendMessage(ChatColor.GREEN + "successly done the modreq of player " + p.getName() + " saving to file for next join");
									fileManager.writeFile(args[0] + ".yml", "date", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "modreq_done");
									fileManager.writeFile(args[0] + ".yml", "comment", "none", fileManager.getDir() + File.separator + "modreq_done");
									fileManager.writeFile(args[0] + ".yml", "helped", sender.getName(), fileManager.getDir() + File.separator + "modreq_done");
								} else {
									sender.sendMessage(ChatColor.RED + p.getName() + " hasn't any modreqs open");
								}
							}
						}
					} else if(args.length > 1) {
						StringBuilder  build = new StringBuilder();
						for(int i = 1; i < args.length; i++) {
							build.append(args[i] + " ").toString();
						}
						Player p = Bukkit.getPlayer(args[0]);
						if(p instanceof Player) {
							if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
								File f = fileManager.returnFile(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq");
								p.sendMessage(ChatColor.GREEN + sender.getName() + " has done your modreq!");
								p.sendMessage(ChatColor.GREEN + "comment: " + build.toString());
								f.delete();
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.hasPermission("xEssentials.command.done")) {
										if(!sender.getName().equalsIgnoreCase(player.getName())) {
											player.sendMessage(ChatColor.GREEN + sender.getName() + " has done " + args[0] + " modreq!");
											player.sendMessage(ChatColor.GREEN + "comment: " + build.toString());
										}
									}
								}
								sender.sendMessage(ChatColor.GREEN + "successly done the modreq of player " + p.getName());
							} else {
								sender.sendMessage(ChatColor.RED + p.getName() + " hasn't any modreqs open");
							}
						} else {
							if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "modreq")) {
								File f = fileManager.returnFile(args[0] + ".yml", fileManager.getDir() + File.separator + "modreq");
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.hasPermission("xEssentials.command.done")) {
										if(!sender.getName().equalsIgnoreCase(player.getName())) {
											player.sendMessage(ChatColor.GREEN + sender.getName() + " has done " + args[0] + " modreq!, but the player is offline saving");
											player.sendMessage(ChatColor.GREEN + "comment: " + build.toString());
										}
									}
								}
								f.delete();
								sender.sendMessage(ChatColor.GREEN + "successly done the modreq of player " + p.getName() + " saving to file for next join");
								fileManager.writeFile(args[0] + ".yml", "date", timeunits.setLongToDate(System.currentTimeMillis()).toString(), fileManager.getDir() + File.separator + "modreq_done");
								fileManager.writeFile(args[0] + ".yml", "comment", build.toString(), fileManager.getDir() + File.separator + "modreq_done");
								fileManager.writeFile(args[0] + ".yml", "helped", sender.getName(), fileManager.getDir() + File.separator + "modreq_done");
							} else {
								sender.sendMessage(ChatColor.RED + p.getName() + " hasn't any modreqs open");
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
