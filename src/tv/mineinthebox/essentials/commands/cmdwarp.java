package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdwarp {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("warp")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.warp")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[warp]___Oo.");
						sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/warp <warpname>" + ChatColor.WHITE + " : let you warp to another place");
						if(sender.hasPermission("xEssentials.command.warp.admin")) {
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/warp <warpname> <player>" + ChatColor.WHITE + " : let another player teleport to a warp");
						}
					} else if(args.length == 1) {
						if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "warps")) {
							Player p = (Player) sender;
							Location loc = p.getLocation();
							loc.setX(fileManager.getDoubleValue(args[0] + ".yml", "x", fileManager.getDir() + File.separator + "warps"));
							loc.setY(fileManager.getDoubleValue(args[0] + ".yml", "y", fileManager.getDir() + File.separator + "warps"));
							loc.setZ(fileManager.getDoubleValue(args[0] + ".yml", "z", fileManager.getDir() + File.separator + "warps"));
							loc.setYaw(fileManager.getIntegerValue(args[0] + ".yml", "yaw", fileManager.getDir() + File.separator + "warps"));
							loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
							if(Bukkit.getWorld(fileManager.getStringValue(args[0] + ".yml", "world", fileManager.getDir() + File.separator + "warps")) instanceof World) {
								if(p.isInsideVehicle()) {
									p.getVehicle().eject();
									sender.sendMessage(ChatColor.GREEN + "successfully ejected from vehicle, warping to " + args[0]);
									p.teleport(loc);
								} else {
									sender.sendMessage(ChatColor.GREEN + "teleporting to warp " + args[0]);
									p.teleport(loc);
								}
							} else {
								sender.sendMessage(ChatColor.RED + "this world is not loaded! " + fileManager.getStringValue(args[0] + ".yml", "world", fileManager.getDir() + File.separator + "warps"));
							}
						} else {
							sender.sendMessage(ChatColor.RED + "couldn't find a warp with this name " + args[0]);
						}
					} else if(args.length == 2) {
						if(sender.hasPermission("xEssentials.command.admin")) {
							if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "warps")) {
								Player p = Bukkit.getPlayer(args[1]);
								if(p instanceof Player) {
									Location loc = p.getLocation();
									loc.setX(fileManager.getDoubleValue(args[0] + ".yml", "x", fileManager.getDir() + File.separator + "warps"));
									loc.setY(fileManager.getDoubleValue(args[0] + ".yml", "y", fileManager.getDir() + File.separator + "warps"));
									loc.setZ(fileManager.getDoubleValue(args[0] + ".yml", "z", fileManager.getDir() + File.separator + "warps"));
									loc.setYaw(fileManager.getIntegerValue(args[0] + ".yml", "yaw", fileManager.getDir() + File.separator + "warps"));
									loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
									if(Bukkit.getWorld(fileManager.getStringValue(args[0] + ".yml", "world", fileManager.getDir() + File.separator + "warps")) instanceof World) {
										if(p.isInsideVehicle()) {
											p.getVehicle().eject();
											sender.sendMessage(ChatColor.GREEN + "successfully ejected from vehicle, " + sender.getName() + " warped you to " + args[0]);
											p.teleport(loc);
										} else {
											sender.sendMessage(ChatColor.GREEN + sender.getName() + " warped you to " + args[0]);
											p.teleport(loc);
										}
									} else {
										sender.sendMessage(ChatColor.RED + "this world is not loaded! " + fileManager.getStringValue(args[0] + ".yml", "world", fileManager.getDir() + File.separator + "warps"));
									}
								} else {
									sender.sendMessage(ChatColor.RED + "couldn't find a warp with this name " + args[0]);
								}
							} else {
								sender.sendMessage(ChatColor.RED + "this player does not exist " + args[1]);
							}
						}
					} else {
						playerPermission.getPermissionError(sender, cmd, args);
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
