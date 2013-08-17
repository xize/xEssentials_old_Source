package tv.mineinthebox.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class consolePermission {
	
	public static void getConsoleMessage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "cannot perform any command on non living entities!");
	}

}
