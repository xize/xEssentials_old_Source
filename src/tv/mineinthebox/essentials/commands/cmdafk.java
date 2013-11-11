package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.events.playerMoveEvent.afkCheck;
import tv.mineinthebox.essentials.permissions.consolePermission;

public class cmdafk {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("afk")) {
			if(sender instanceof Player) {
				if(args.length == 0) {
					Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " has been afk");
					afkCheck.afkList.add(sender.getName());
				} else if(args.length > 0) {
					StringBuilder build = new StringBuilder();
					for(int i = 0; i < args.length; i++) {
						build.append(args[i] + " ").toString();
					}
					Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " has been afk [ " + build.toString() + "]");
					afkCheck.afkMessage.put(sender.getName(), build.toString());
					afkCheck.afkList.add(sender.getName());
				}
			} else {
				consolePermission.getConsoleMessage(sender);
			}
		}
		return false;
	}

}
