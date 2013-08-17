package tv.mineinthebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.events.playermove;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdfirefly {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("firefly")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.firefly")) {
					if(playermove.firefly.contains(sender.getName())) {
						playermove.firefly.remove(sender.getName());
						sender.sendMessage(ChatColor.GREEN + "disabled firefly!");
					} else {
						playermove.firefly.add(sender.getName());
						sender.sendMessage(ChatColor.GREEN + "enabled firefly!");
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
