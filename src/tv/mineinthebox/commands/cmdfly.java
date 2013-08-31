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

public class cmdfly {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(sender.hasPermission("xEssentials.command.fly")) {
				if(args.length == 0) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "players")) {
							if(fileManager.isSet(sender.getName()  + ".yml", "fly", fileManager.getDir() + File.separator + "players")) {
								if(fileManager.getBooleanValue(sender.getName() + ".yml", "fly", fileManager.getDir() + File.separator + "players")) {
									fileManager.writeFile(sender.getName() + ".yml", "fly", false, fileManager.getDir() + File.separator + "players");
									p.sendMessage(ChatColor.GREEN + "successfully disabled survival fly!");
									p.setAllowFlight(false);
									p.setFlying(false);
								} else {
									fileManager.writeFile(sender.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
									p.sendMessage(ChatColor.GREEN + "successfully enabled survival fly!");
									p.setAllowFlight(true);
									p.setFlying(true);
								}
							} else {
								fileManager.writeFile(sender.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
								p.sendMessage(ChatColor.GREEN + "successfully enabled survival fly!");
								p.setAllowFlight(true);
								p.setFlying(true);
							}
						} else {
							fileManager.writeFile(sender.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
							p.sendMessage(ChatColor.GREEN + "successfully enabled survival fly!");
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					} else {
						consolePermission.getConsoleMessage(sender);
					}
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[fly help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/fly " + ChatColor.WHITE + ": enables survival fly mode");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/fly <player> " + ChatColor.WHITE + ": enables survival fly mode for a player");
					} else {
						Player player = Bukkit.getPlayer(args[0]);
						if(player instanceof Player) {
							if(fileManager.file_exists(player.getName() + ".yml", fileManager.getDir() + File.separator + "players")) {
								if(fileManager.isSet(player.getName() + ".yml", "fly", fileManager.getDir() + File.separator + "players")) {
									if(fileManager.getBooleanValue(player.getName() + ".yml", "fly", fileManager.getDir() + File.separator + "players")) {
										fileManager.writeFile(player.getName() + ".yml", "fly", false, fileManager.getDir() + File.separator + "players");
										player.setAllowFlight(false);
										player.setFlying(false);
										player.sendMessage(ChatColor.RED + sender.getName() + " has disabled your fly mode!");
									} else {
										fileManager.writeFile(player.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
										player.setAllowFlight(true);
										player.setFlying(true);
										player.sendMessage(ChatColor.GREEN + sender.getName() + " has enabled your fly mode!");
									}
								} else {
									fileManager.writeFile(player.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
									player.setAllowFlight(true);
									player.setFlying(true);
									player.sendMessage(ChatColor.GREEN + sender.getName() + " has enabled your fly mode!");
								}
							} else {
								fileManager.writeFile(player.getName() + ".yml", "fly", true, fileManager.getDir() + File.separator + "players");
								player.setAllowFlight(true);
								player.setFlying(true);
								player.sendMessage(ChatColor.GREEN + sender.getName() + " has enabled your fly mode!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "warning this player is not online!");
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
