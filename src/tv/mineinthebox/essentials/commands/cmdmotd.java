package tv.mineinthebox.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdmotd {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("motd")) {
			if(sender.hasPermission("xEssentials.command.motd")) {
				fileManager.writeFile("motd.yml", "message", args, fileManager.getDir());
				sender.sendMessage(ChatColor.GREEN + "successfully changed motd to " + args);
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
		
	}

}
