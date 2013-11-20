package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdsetwarp {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setwarp")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.setwarp")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/setwarp <warpname> " + ChatColor.WHITE + ": ");
					} else if(args.length == 1) {
						Player p = (Player) sender;
						if(fileManager.file_exists(args[0].toLowerCase() + ".yml", fileManager.getDir() + File.separator + "warps")) {
							if(sender.getName().equalsIgnoreCase(fileManager.getStringValue(args[0].toLowerCase() + ".yml", "warpOwner", fileManager.getDir() + File.separator + "warps"))) {
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "warps");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "warps");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "warps");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "warps");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "warps");
								fileManager.writeFile(args[0].toLowerCase() + ".yml", "warpOwner", sender.getName(), fileManager.getDir() + File.separator + "warps");
								sender.sendMessage(ChatColor.GREEN + "successfully saved new location of the warp " + args[0]);
							} else {
								sender.sendMessage(ChatColor.RED + "you are not allowed to overwrite a warp!");
							}
						} else {
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "x", p.getLocation().getX(), fileManager.getDir() + File.separator + "warps");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "y", p.getLocation().getY(), fileManager.getDir() + File.separator + "warps");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "z", p.getLocation().getZ(), fileManager.getDir() + File.separator + "warps");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "yaw", p.getLocation().getYaw(), fileManager.getDir() + File.separator + "warps");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "world", p.getWorld().getName(), fileManager.getDir() + File.separator + "warps");
							fileManager.writeFile(args[0].toLowerCase() + ".yml", "warpOwner", sender.getName(), fileManager.getDir() + File.separator + "warps");
							sender.sendMessage(ChatColor.GREEN + "successfully saved warp " + args[0]);
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
