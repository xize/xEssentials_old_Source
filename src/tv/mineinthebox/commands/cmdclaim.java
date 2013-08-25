package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;

public class cmdclaim {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("claim")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.claim")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[modreq claim]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/claim <player>" + ChatColor.WHITE + " : claims a modreq of a player");
					} else if(args.length == 1) {
						Player p = Bukkit.getPlayer(args[0]);
						if(p instanceof Player) {
							if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "modreq")) {
								p.sendMessage(ChatColor.GOLD + "staff member " + sender.getName() + " has claimed your modreq!");
								sender.sendMessage(ChatColor.GOLD + "successfully claimed modreq for player " + p.getName());
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.hasPermission("xEssentials.isStaff")) {
										if(!player.getName().equalsIgnoreCase(sender.getName())) {
											player.sendMessage(ChatColor.GOLD + sender.getName() + " has claimed the modreq for player " + p.getName());
										}
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "this player has no modreqs open!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "warning this player is not online!, you cannot claim modreqs while players are offline");
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
