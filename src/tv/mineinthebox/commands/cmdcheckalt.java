package tv.mineinthebox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.permissions.playerPermission;
import tv.mineinthebox.resources.bansystem.ban;

public class cmdcheckalt {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("checkalt")) {
			if(sender.hasPermission("xEssentials.isStaff")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[altcheck help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/altcheck <player> " + ChatColor.WHITE + ": checks");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[altcheck help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/altcheck <player> " + ChatColor.WHITE + ": checks");
					} else {
						Player p = Bukkit.getPlayer(args[0]);
						if(p instanceof Player) {
							ban.getAlternateAccounts(sender, p);
						} else {
							ban.getAlternateAccounts(sender, args[0]);
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
