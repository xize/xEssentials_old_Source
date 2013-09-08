package tv.mineinthebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdfirefly {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("firefly")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(sender.hasPermission("xEssentials.command.firefly")) {
					if(xEssentialsMemory.returnPlayer(p).containsKey("firefly")) {
						if((Boolean) xEssentialsMemory.returnPlayer(p).get("firefly")) {
							xEssentialsMemory.returnPlayer(p).put("firefly", false);
							xEssentialsMemory.updatePlayerConfig(p);
							sender.sendMessage(ChatColor.GREEN + "disabled firefly!");
						} else {
							xEssentialsMemory.returnPlayer(p).put("firefly", true);
							xEssentialsMemory.updatePlayerConfig(p);
							sender.sendMessage(ChatColor.GREEN + "enabled firefly!");
						}
					} else {
						playerPermission.getPermissionError(sender, cmd, args);
					}
				} else {
					consolePermission.getConsoleMessage(sender);
				}
			}
		}
		return false;
	}
}