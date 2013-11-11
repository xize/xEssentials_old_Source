package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import tv.mineinthebox.essentials.events.signEvent.signBoom;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdboom {
	public static boolean execute(CommandSender sender, String[] args, Command cmd) {
		if(cmd.getName().equalsIgnoreCase("boom")) {
			if(sender.hasPermission("xEssentials.command.boom")) {
				if(args.length == 1) {
					Player boom = Bukkit.getPlayer(args[0]);
					if(boom instanceof Player) {
						signBoom.setInArray(boom.getName());
						sender.sendMessage(ChatColor.GREEN + "You are boomed!");
						Bukkit.broadcastMessage(ChatColor.GRAY + "The player " + ChatColor.GOLD +  args[0] + ChatColor.GRAY + " has been boomed by " + ChatColor.GREEN + sender.getName());
						Location loc = boom.getPlayer().getLocation();
						loc.setY(loc.getY() + 100);
						int speed = 10;
						Vector vector = loc.toVector().subtract(boom.getLocation().toVector()).normalize();
						boom.setVelocity(vector.multiply(speed));
					} else {
						sender.sendMessage(ChatColor.RED + "This player is not online");
					}
				} else {
					sender.sendMessage(ChatColor.GREEN + "Syntax: /boom <player> - explodes a player high in the sky.");
				}
			}
		} else {
			playerPermission.getPermissionError(sender, cmd, args);
		}
			return false;
	}
}
