package tv.mineinthebox.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class playerPermission {
	
	public static void getPermissionError(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.sethome");
		} else if(cmd.getName().equalsIgnoreCase("home")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.home");
		} else if(cmd.getName().equalsIgnoreCase("setspawn")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.setspawn");
		}
	}

}
