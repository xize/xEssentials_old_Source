package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdheal {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("heal")) {
			if(sender.hasPermission("xEssentials.command.heal")) {
				if(args.length == 0) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						p.setHealth(20);
						p.setFoodLevel(20);
						p.getPlayer().sendMessage(ChatColor.GREEN + "you are healed!");
					} else {
						consolePermission.getConsoleMessage(sender);
					}
				} else if(args.length == 1) {
					Player p = Bukkit.getPlayer(args[0]);
					if(p instanceof Player) {
						p.setHealth(20);
						p.setFoodLevel(20);
						p.getPlayer().sendMessage(ChatColor.GREEN + "you are healed by " + sender.getName() + "!");
						sender.sendMessage(ChatColor.GREEN + "successfully healed player " + args[0]);
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
