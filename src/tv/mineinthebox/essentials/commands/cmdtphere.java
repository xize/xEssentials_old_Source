package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdtphere {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tphere")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(sender.hasPermission("xEssentials.command.tphere")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[tphere]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tphere <player> " + ChatColor.WHITE + ": teleports a player to you");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tphere all " + ChatColor.WHITE + ": teleports all players to you");
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[tphere]___Oo.");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tphere <player> " + ChatColor.WHITE + ": teleports a player to you");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/tphere all " + ChatColor.WHITE + ": teleports all players to you");
						} else if(args[0].equalsIgnoreCase("all")) {
							for(Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(ChatColor.GREEN + sender.getName() + " has teleported all players to his location!");
								player.teleport(p);
							}
						} else {
							Player victem = Bukkit.getPlayer(args[0]);
							if(victem instanceof Player) {
								victem.sendMessage(ChatColor.GREEN + sender.getName() + " has teleported you to his location");
								victem.teleport(p);
								sender.sendMessage(ChatColor.GREEN + "successfully teleported " + victem.getName() + " to you.");
							} else {
								sender.sendMessage(ChatColor.RED + "warning this player is not online!");
							}
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
