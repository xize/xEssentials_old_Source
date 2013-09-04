package tv.mineinthebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdfirefly {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("firefly")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(sender.hasPermission("xEssentials.command.firefly")) {
					if(xEssentials.mem.returnPlayer(p).containsKey("firefly")) {
						if((Boolean) xEssentials.mem.returnPlayer(p).get("firefly")) {
							xEssentials.mem.returnPlayer(p).put("firefly", false);
							xEssentials.mem.updatePlayerConfig(p);
							sender.sendMessage(ChatColor.GREEN + "disabled firefly!");
						} else {
							xEssentials.mem.returnPlayer(p).put("firefly", true);
							xEssentials.mem.updatePlayerConfig(p);
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