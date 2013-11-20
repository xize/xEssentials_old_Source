package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdhomeinvite {

	public static HashMap<String, String> playerInvites = new HashMap<String, String>();

	public static boolean execute(final CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("homeinvite")) {
			if(sender instanceof Player) {
				final Player p = (Player) sender;
				if(sender.hasPermission("xEssentials.command.homeinvite")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[home invite help]___Oo.");
						sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite <player> " + ChatColor.WHITE + ": invite a player to your home!");
						sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite accept " + ChatColor.WHITE + ": accept the invite to teleport to the players home!");
						sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite deny " + ChatColor.WHITE + ": refuse the invite to teleport to the players home!");
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[home invite help]___Oo.");
							sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite <player> " + ChatColor.WHITE + ": invite a player to your home!");
							sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite accept " + ChatColor.WHITE + ": accept the invite to teleport to the players home!");
							sender.sendMessage(ChatColor.GRAY + "Default: " + ChatColor.GRAY + "/homeinvite deny " + ChatColor.WHITE + ": refuse the invite to teleport to the players home!");
						} else if(args[0].equalsIgnoreCase("accept")) {
							if(playerInvites.containsKey(sender.getName().toLowerCase())) {
								Player requester = Bukkit.getPlayer(playerInvites.get(sender.getName()));
								if(requester instanceof Player) {
									Location loc = new Location(Bukkit.getWorld(fileManager.getStringValue(requester.getName().toLowerCase() + ".yml", "World", fileManager.getDir() + File.separator + "homes")), fileManager.getDoubleValue(requester.getName().toLowerCase() + ".yml", "x", fileManager.getDir() + File.separator + "homes"), fileManager.getDoubleValue(requester.getName().toLowerCase() + ".yml", "y", fileManager.getDir() + File.separator + "homes"), fileManager.getDoubleValue(requester.getName().toLowerCase() + ".yml", "z", fileManager.getDir() + File.separator + "homes"), fileManager.getIntegerValue(requester.getName().toLowerCase() + ".yml", "yaw", fileManager.getDir() + File.separator + "homes"), requester.getLocation().getPitch());
									cmdteleport.teleport(p, loc);
									requester.sendMessage(ChatColor.GREEN + p.getName() + " has successfully accepted your request");
									p.sendMessage(ChatColor.GREEN + "you have accepted home request for player " + requester.getName());
									playerInvites.remove(sender.getName().toLowerCase());
								} else {
									sender.sendMessage(ChatColor.RED + requester.getName() + " is not online!");
									playerInvites.remove(sender.getName().toLowerCase());
								}
							} else {
								sender.sendMessage(ChatColor.RED + "you do not have open homeinvites!");
							}
						} else if(args[0].equalsIgnoreCase("deny")) {
							if(playerInvites.containsKey(sender.getName().toLowerCase())) {
								Player requester = Bukkit.getPlayer(playerInvites.get(sender.getName()));
								if(requester instanceof Player) {
									requester.sendMessage(ChatColor.RED + sender.getName() + " has denied your homeinvite request!");
									sender.sendMessage(ChatColor.GREEN + "successfully denied homeinvited request for player " + requester.getName());
									playerInvites.remove(sender.getName().toLowerCase());
								} else {
									sender.sendMessage(ChatColor.RED + requester.getName() + " is not online!");
									playerInvites.remove(sender.getName().toLowerCase());
								}
							} else {
								sender.sendMessage(ChatColor.RED + "you do not have open homeinvites!");
							}
						} else {
							final Player accepter = Bukkit.getPlayer(args[0]);
							if(accepter instanceof Player) {
								accepter.sendMessage(ChatColor.GREEN + sender.getName() + " wants you to teleport to his home");
								accepter.sendMessage(ChatColor.GREEN + "type /homeinvite accept or /homeinvite deny");
								sender.sendMessage(ChatColor.GREEN + "homeinvite request successfully sent to player " + accepter.getName());
								final String accepterName = accepter.getName();
								final String inviterName = p.getName();
								playerInvites.put(accepter.getName().toLowerCase(), sender.getName().toLowerCase());
								Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

									@Override
									public void run() {
										if(!playerInvites.containsKey(accepter.getName().toLowerCase())) {
											return;
										}
										if(accepter instanceof Player) {
											accepter.sendMessage(ChatColor.RED + "the homeinvite from player " + inviterName + " has been canceled out");
										}
										if(p instanceof Player) {
											p.sendMessage(ChatColor.RED + "your homeinvite to " + accepterName + " has been canceled out");
										}
										playerInvites.remove(accepterName.toLowerCase());
									}

								}, 1000);
							} else {
								sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
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
