package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.tpback.back;

public class cmdback {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("back")) {
			if(sender.hasPermission("xEssentials.command.back")) {
				if(args.length == 0) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(back.locations.containsKey(p.getName())) {
							Location loc = back.locations.get(sender.getName());
							cmdteleport.teleport(p, loc);
						} else {
							sender.sendMessage(ChatColor.RED + "there whas no back data found!");
						}
					} else {
						consolePermission.getConsoleMessage(sender);
					}
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[back command help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/back " + ChatColor.WHITE + ": teleports back to your last location");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/back <player>" + ChatColor.WHITE + ": teleports a player back to the last location");
					} else {
						Player p = Bukkit.getPlayer(args[0]);
						if(p instanceof Player) {
							Location loc = back.locations.get(sender.getName());
							cmdteleport.teleport(p, loc);
						} else {
							sender.sendMessage(ChatColor.RED + "this player is not online!");
							if(back.locations.containsKey(args[0])) {
								back.locations.remove(args[0]);
							}
						}
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args); 
			}
		}
		return false;
	}

}
