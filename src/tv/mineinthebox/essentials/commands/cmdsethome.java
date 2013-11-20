package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdsethome {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.sethome")) {
					Player p = (Player) sender;
					if(fileManager.file_exists(sender.getName().toLowerCase() + ".yml", fileManager.getDir() + File.separator + "homes")) {
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "World", p.getWorld().getName(), fileManager.getDir() + File.separator + "homes");
						sender.sendMessage(ChatColor.GREEN + "you've successfully set your new home :)");
					} else {
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "homes");
						fileManager.writeFile(sender.getName().toLowerCase() + ".yml", "World", p.getWorld().getName(), fileManager.getDir() + File.separator + "homes");
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
