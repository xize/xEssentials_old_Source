package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.worldguard;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class cmdvanish {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vanish")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.vanish")) {
					Player p = (Player) sender;
					if(args.length == 0) {
						if(vanishApi.isVanished(p)) {
							vanishApi.unvanish(p);
							p.sendMessage(ChatColor.GREEN + "you are unvanished!");
						} else {
							vanishApi.vanish(p);
							p.sendMessage(ChatColor.GREEN + "you are vanished!");
						}
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("nopickup")) {
							if(p.hasPermission("xEssentials.command.vanish.nopickup")) {
								if(vanishApi.isVanished(p)) {
									if(vanishApi.vanishNoPickUp(p)) {
										vanishApi.unsetNoPickUp(p);
										p.sendMessage(ChatColor.GREEN + "you can now pickup items!");
									} else {
										vanishApi.setNoPickUp(p);
										p.sendMessage(ChatColor.GREEN + "you no longer can pickup items!");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "you can only change pickup behaviour when you are vanished!");
								}
							} else {
								playerPermission.getPermissionError(sender, cmd, args);
							}
						} else if(args[0].equalsIgnoreCase("fq") || args[0].equalsIgnoreCase("fakequit")) {
							if(hooks.isWorldGuardEnabled()) {
								worldguard.sendVanishQuitMessage(p);
							} else {
								Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has left!");
								vanishApi.vanish(p);
							}
						} else if(args[0].equalsIgnoreCase("fj") || args[0].equalsIgnoreCase("fakejoin")) {
							if(hooks.isWorldGuardEnabled()) {
								worldguard.sendVanishJoinMessage(p);
							} else {
								Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has joined :)");
								vanishApi.unvanish(p);
							}
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
