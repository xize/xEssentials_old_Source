package tv.mineinthebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdsetspawn {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.setspawn")) {
					Player p = (Player) sender;
					Location loc = p.getLocation();
					Double x = loc.getX();
					Double y = loc.getY();
					Double z = loc.getZ();
					int yaw = (int) loc.getYaw();
					fileManager.writeFile("spawn.yml", "x", x, fileManager.getDir());
					fileManager.writeFile("spawn.yml", "y", y, fileManager.getDir());
					fileManager.writeFile("spawn.yml", "z", z, fileManager.getDir());
					fileManager.writeFile("spawn.yml", "yaw", yaw, fileManager.getDir());
					fileManager.writeFile("spawn.yml", "world", loc.getWorld().getName(), fileManager.getDir());
					sender.sendMessage(ChatColor.GREEN + "successfully saved spawn!");
					p.getLocation().getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
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
