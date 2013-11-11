package tv.mineinthebox.essentials.commands;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

public class cmdmute {
	
	@SuppressWarnings("deprecation")
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mute")) {
			if(sender.hasPermission("xEssentials.command.mute")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[mute help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mute player 1D 5m" + ChatColor.WHITE + " : mutes a player for 1 day and 5 minutes");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[mute help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mute player 1D 5m" + ChatColor.WHITE + " : mutes a player for 1 day and 5 minutes");
					} else {
						Player victem = Bukkit.getPlayer(args[0]);
						if(victem instanceof Player) {
							Date date = timeunits.setLongToDate(System.currentTimeMillis());
							date.setDate(date.getDate() + 1);
							xEssentialsMemory.returnPlayer(victem).put("muted", date.getTime());
							xEssentialsMemory.updatePlayerConfig(victem);
							victem.sendMessage(ChatColor.GREEN + "you where muted by " + sender.getName() + " for " + timeunits.getElapsedTime(date.getTime()));
							sender.sendMessage(ChatColor.GREEN + "successfully muted " + victem.getName() + " for " + timeunits.getElapsedTime(date.getTime()));
						} else {
							sender.sendMessage(ChatColor.RED + "this player is not online!");
						}
					}
				} else {
					Player victem = Bukkit.getPlayer(args[0]);
					if(victem instanceof Player) {
						Long time = timeunits.convertDateArguments(args, sender);
						Date date = timeunits.setLongToDate(time);
						xEssentialsMemory.returnPlayer(victem).put("muted", date.getTime());
						xEssentialsMemory.updatePlayerConfig(victem);
						victem.sendMessage(ChatColor.GREEN + "you where muted by " + sender.getName() + " for " + timeunits.getElapsedTime(date.getTime()));
						sender.sendMessage(ChatColor.GREEN + "successfully muted " + victem.getName() + " for " + timeunits.getElapsedTime(date.getTime()));
					} else {
						sender.sendMessage(ChatColor.RED + "this player is not online!");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
