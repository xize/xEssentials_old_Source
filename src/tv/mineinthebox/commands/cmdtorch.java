package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdtorch {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("torch")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.torch")) {
					if(fileManager.file_exists(sender.getName() + ".yml", fileManager.getDir() + File.separator + "players")) {
						if(fileManager.isSet(sender.getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players")) {
							if(fileManager.getBooleanValue(sender.getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players")) {
								fileManager.writeFile(sender.getName() + ".yml", "torch", false, fileManager.getDir() + File.separator + "players");
								sender.sendMessage(ChatColor.GREEN + "successfully disabled torch");
							} else {
								fileManager.writeFile(sender.getName() + ".yml", "torch", true, fileManager.getDir() + File.separator + "players");
								sender.sendMessage(ChatColor.GREEN + "successfully enabled torch");
							}
						} else {
							fileManager.writeFile(sender.getName() + ".yml", "torch", true, fileManager.getDir() + File.separator + "players");
							sender.sendMessage(ChatColor.GREEN + "successfully enabled torch");
						}
					} else {
						fileManager.writeFile(sender.getName() + ".yml", "torch", true, fileManager.getDir() + File.separator + "players");
						sender.sendMessage(ChatColor.GREEN + "successfully enabled torch");
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
