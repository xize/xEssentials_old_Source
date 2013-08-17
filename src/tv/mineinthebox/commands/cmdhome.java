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
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.home")) {
					if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "homes" + File.separator)) {
						Player p = (Player) sender;
						Location loc = p.getLocation();
						Double x = fileManager.getDoubleValue(sender.getName() + ".yml", "x", fileManager.getDir() + File.separator + "homes");
						Double y = fileManager.getDoubleValue(sender.getName() + ".yml", "y", fileManager.getDir() + File.separator + "homes");
						Double z = fileManager.getDoubleValue(sender.getName() + ".yml", "z", fileManager.getDir() + File.separator + "homes");
						Float yaw = fileManager.getFloatValue(sender.getName() + ".yml", "yaw", fileManager.getDir() + File.separator + "homes");
						World w = Bukkit.getWorld(fileManager.getStringValue(sender.getName() + ".yml", "World", fileManager.getDir() + File.separator + "homes"));
						if(w instanceof World) {
							loc.setX(x);
							loc.setY(y);
							loc.setZ(z);
							loc.setYaw(yaw);
							loc.setWorld(w);
							loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
							if(p.isInsideVehicle()) {
								p.getVehicle().eject();
								p.sendMessage(ChatColor.GREEN + "successfully ejected from vehicle, teleporting to home!");
								p.teleport(loc);
							} else {
								p.sendMessage(ChatColor.GREEN + "teleporting to home :)");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "your home is in a unloaded world!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "your home does not exists");
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
