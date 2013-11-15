 package tv.mineinthebox.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdtorch {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("torch")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.torch")) {
					if(args.length == 0) {
						Player p = (Player) sender;
						if(xEssentialsMemory.returnPlayer(p).containsKey("torch")) {
							Boolean bol = (Boolean) xEssentialsMemory.returnPlayer(p).get("torch");
							if(bol) {
								sender.sendMessage(ChatColor.GREEN + "you successfully turned off torch");
								xEssentialsMemory.returnPlayer(p).put("torch", false);
								xEssentialsMemory.updatePlayerConfig(p);
							} else {
								sender.sendMessage(ChatColor.GREEN + "you successfully turned on torch");
								xEssentialsMemory.returnPlayer(p).put("torch", true);
								xEssentialsMemory.updatePlayerConfig(p);
							}
						} else {
							sender.sendMessage(ChatColor.GREEN + "you successfully turned on torch");
							xEssentialsMemory.returnPlayer(p).put("torch", true);
							xEssentialsMemory.updatePlayerConfig(p);
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
