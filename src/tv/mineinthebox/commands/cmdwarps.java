package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdwarps {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("warps")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.warps")) {
					StringBuilder build = new StringBuilder();
					for(File f : fileManager.getFileList(fileManager.getDir() + File.separator + "warps")) {
						build.append(f.getName().replace(".yml", "") + ", ").toString();
					}
					sender.sendMessage(ChatColor.GOLD + ".oO___[warps]___Oo.");
					sender.sendMessage(ChatColor.GRAY + build.toString());
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
