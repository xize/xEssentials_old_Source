package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmddelwarp {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("delwarp")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.delwarp")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.RED + "you where missing one argument, syntax: /delwarp <warpname>");
					} else if(args.length == 1) {
						if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "warps")) {
							if(sender.hasPermission("xEssentials.command.delwarp.admin") || fileManager.getStringValue(args[0] + ".yml", "warpOwner", fileManager.getDir() + File.separator + "warps").equalsIgnoreCase(sender.getName())) {
								fileManager.returnFile(args[0] + ".yml", fileManager.getDir() + File.separator + "warps").delete();
								sender.sendMessage(ChatColor.GREEN + "successfully removed warp " + args[0]);
							} else {
								sender.sendMessage(ChatColor.RED + "warning this warp does not belongs to you!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "warp " + args[0] + " whas not found!");
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
