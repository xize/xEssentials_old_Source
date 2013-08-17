package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdsethome {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.sethome")) {
					Player p = (Player) sender;
					if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "homes")) {
						fileManager.writeFile(sender.getName() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "World", p.getWorld().getName(), fileManager.getDir() + File.separator + "homes");
						sender.sendMessage(ChatColor.GREEN + "you've successfully set your new home :)");
					} else {
						fileManager.writeFile(sender.getName() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName() + ".yml", "World", p.getWorld().getName(), fileManager.getDir() + File.separator + "homes");
						sender.sendMessage(ChatColor.GREEN + "you've successfully set your home :)");
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
