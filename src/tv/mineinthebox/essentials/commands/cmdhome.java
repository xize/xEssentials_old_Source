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

public class cmdhome {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("home")) {
			if(sender instanceof Player) {
				if(args.length == 0) {
					if(sender.hasPermission("xEssentials.command.home")) {
						if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "homes")) {
							Player p = (Player) sender;
							Location loc = p.getLocation();
							loc.setX(fileManager.getDoubleValue(p.getName() + ".yml", "x", fileManager.getDir() + File.separator + "homes"));
							loc.setY(fileManager.getDoubleValue(p.getName() + ".yml", "y", fileManager.getDir() + File.separator + "homes"));
							loc.setZ(fileManager.getDoubleValue(p.getName() + ".yml", "z", fileManager.getDir() + File.separator + "homes"));
							loc.setYaw(fileManager.getIntegerValue(p.getName() + ".yml", "yaw", fileManager.getDir() + File.separator + "homes"));
							if(Bukkit.getWorld(fileManager.getStringValue(p.getName() + ".yml", "World", fileManager.getDir() + File.separator + "homes")) instanceof World) {
								World w = Bukkit.getWorld(fileManager.getStringValue(p.getName() + ".yml", "World", fileManager.getDir() + File.separator + "homes"));
								loc.setWorld(w);
								loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
								if(p.isInsideVehicle()) {
									p.getVehicle().eject();
									p.sendMessage(ChatColor.GREEN + "successfully ejected from vehicle teleporting to home :)");
									p.teleport(loc);
								} else {
									p.sendMessage(ChatColor.GREEN + "teleporting to home! :)");
									p.teleport(loc);
								}
							} else {
								sender.sendMessage(ChatColor.RED + "your home is in a world which is unloaded!");
							}
						}
					} else {
						playerPermission.getPermissionError(sender, cmd, args);
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("convert")) {
						if(sender.hasPermission("xEssentials.command.homeconvert")) {
							if(args[1].equalsIgnoreCase("essentials")) {
								if(args[1].equalsIgnoreCase("essentials")) {
									if(fileManager.isDirectory(fileManager.getBukkitDir() + File.separator + "essentials")) {
										sender.sendMessage(ChatColor.GREEN + "converting all essentials default homes to xEssentials homes!");
										File[] list = fileManager.getFileList(fileManager.getBukkitDir() + File.separator + "essentials");
										for(File file : list) {
											convertEssentials(file);
										}
									} else {
										sender.sendMessage(ChatColor.RED + "no essentials user folder found!");
									}
								}
							}
						} else {
							playerPermission.getPermissionError(sender, cmd, args);
						}
					}
				}
			} else {
				consolePermission.getConsoleMessage(sender);
			}
		}
		return false;
	}

	public static void convertEssentials(File file) {
		double x = fileManager.getDoubleValue(file.getName(), "homes.home.x", fileManager.getBukkitDir() + File.separator + "essentials");
		double y = fileManager.getDoubleValue(file.getName(), "homes.home.y", fileManager.getBukkitDir() + File.separator + "essentials");
		double z = fileManager.getDoubleValue(file.getName(), "homes.home.z", fileManager.getBukkitDir() + File.separator + "essentials");
		int yaw = fileManager.getIntegerValue(file.getName(), "homes.home.yaw", fileManager.getBukkitDir() + File.separator + "essentials");
		String world = fileManager.getStringValue(file.getName(), "homes.home.world", fileManager.getBukkitDir() + File.separator + "essentials");
		if(fileManager.file_exists(file.getName(), fileManager.getDir() + File.separator + "homes")) {
			fileManager.writeFile(file.getName(), "x", x, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "y", y, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "z", z, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "yaw", yaw, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "World", world, fileManager.getDir() + File.separator + "homes");
		} else {
			fileManager.writeFile(file.getName(), "x", x, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "y", y, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "z", z, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "yaw", yaw, fileManager.getDir() + File.separator + "homes");
			fileManager.writeFile(file.getName(), "World", world, fileManager.getDir() + File.separator + "homes");
		}
	}

}
