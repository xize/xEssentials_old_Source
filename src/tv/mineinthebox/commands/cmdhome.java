package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdhome {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("home")) {
			if(sender instanceof Player) {
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
			} else {
				consolePermission.getConsoleMessage(sender);
			}
		}
		return false;
	}

}
