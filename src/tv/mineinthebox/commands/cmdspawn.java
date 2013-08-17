package tv.mineinthebox.commands;

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

public class cmdspawn {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.spawn")) {
					if(fileManager.file_exists("spawn.yml", fileManager.getDir())) {
						Player p = (Player) sender;
						Location loc = p.getLocation();
						loc.setX(fileManager.getDoubleValue("spawn.yml", "x", fileManager.getDir()));
						loc.setY(fileManager.getDoubleValue("spawn.yml", "y", fileManager.getDir()));
						loc.setZ(fileManager.getDoubleValue("spawn.yml", "z", fileManager.getDir()));
						loc.setYaw(fileManager.getIntegerValue("spawn.yml", "yaw", fileManager.getDir()));
						String wname = fileManager.getStringValue("spawn.yml", "world", fileManager.getDir());
						if(Bukkit.getWorld(wname) instanceof World) {
							loc.setWorld(Bukkit.getWorld(wname));
							if(p.isInsideVehicle()) {
								p.getVehicle().eject();
								p.sendMessage(ChatColor.GREEN + "successfully ejected from the vehicle, teleporting to spawn");
								p.teleport(loc);
							} else {
								p.sendMessage(ChatColor.GREEN + "teleporting to spawn");
								p.teleport(loc);
							}
						} else {
							sender.sendMessage(ChatColor.RED + "the spawn has been set in a unloaded world!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "there is currently no spawn set!");
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
