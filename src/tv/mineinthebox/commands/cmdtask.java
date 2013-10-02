package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.playerPermission;

public class cmdtask {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("task")) {
			if(sender.hasPermission("xEssentials.command.task")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[Task help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/task <player> msg hi" + ChatColor.WHITE + " : when the player joins next, the console will use /msg <player> hi");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[Task help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/task <player> msg hi" + ChatColor.WHITE + " : when the player joins next, the console will use /msg <player> hi");	
					} else {
						sender.sendMessage(ChatColor.RED + "oh oh, we don't know nothing about this argument!");
					}
				} else if(args.length > 1) {
					if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "players")) {
						String command = null;
						for(int i = 1; i < args.length; i++) {
							if(command == null) {
								command = command + args[i];
							} else {
								command = command + " " + args[i];	
							}
						}
						fileManager.writeFile(args[0] + ".yml", "taskCommand", command.replace("null", ""), fileManager.getDir() + File.separator + "players");
						sender.sendMessage(ChatColor.GREEN + "successfully saved task command for player " + args[0] + " :)");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
