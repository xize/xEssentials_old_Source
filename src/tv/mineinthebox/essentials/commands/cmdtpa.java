package tv.mineinthebox.essentials.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdtpa {

	private static HashMap<String, String> players = new HashMap<String, String>();

	public static boolean execute(final CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tpa")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.tpa")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[Tpa help]___Oo.");
						sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa <player> " + ChatColor.WHITE + ": sents a tpa request to a player");
						sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa accept " + ChatColor.WHITE + ": accept a request");
						sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa deny " + ChatColor.WHITE + ": deny a request");
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[Tpa help]___Oo.");
							sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa <player> " + ChatColor.WHITE + ": sents a tpa request to a player");
							sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa accept " + ChatColor.WHITE + ": accept a request");
							sender.sendMessage(ChatColor.DARK_GRAY + "Default: " + ChatColor.GRAY + "/tpa deny " + ChatColor.WHITE + ": deny a request");
						} else if(args[0].equalsIgnoreCase("accept")) {
							if(players.containsKey(sender.getName())) {
								Player accepter = (Player) sender;
								Player inviter = Bukkit.getPlayer(players.get(accepter.getName().toLowerCase()));
								if(accepter instanceof Player && inviter instanceof Player) {
									accepter.sendMessage(ChatColor.GREEN + "you accepted " + inviter.getName() + " his request!");
									inviter.sendMessage(ChatColor.GREEN + accepter.getName() + " has accepted your request!");
									cmdteleport.teleport(inviter, accepter);
									players.remove(accepter.getName().toLowerCase());
								} else {
									sender.sendMessage(ChatColor.RED + "it seems that " + players.get(accepter.getName().toLowerCase()) + " is no longer online!");
									players.remove(accepter.getName().toLowerCase());
								}
							} else {
								sender.sendMessage(ChatColor.RED + "you don't have any tpa requests open!");
							}
						} else if(args[0].equalsIgnoreCase("deny")) {
							if(players.containsKey(sender.getName())) {
								Player denier = (Player) sender;
								Player inviter = Bukkit.getPlayer(players.get(denier.getName().toLowerCase()));
								if(denier instanceof Player && inviter instanceof Player) {
									denier.sendMessage(ChatColor.GREEN + "you denied " + inviter.getName() + " his request!");
									inviter.sendMessage(ChatColor.GREEN + denier.getName() + " has denied your request!");
									players.remove(denier.getName().toLowerCase());
								} else {
									sender.sendMessage(ChatColor.RED + "it seems that " + players.get(denier.getName().toLowerCase()) + " is no longer online!");
									players.remove(denier.getName().toLowerCase());
								}
							} else {
								sender.sendMessage(ChatColor.RED + "you don't have any tpa requests open!");
							}
						} else {
							final Player victem = Bukkit.getPlayer(args[0]);
							if(victem instanceof Player) {
								if(!players.containsKey(victem.getName().toLowerCase())) {
									victem.sendMessage(ChatColor.GREEN + sender.getName() + " wants to teleport to you\n/tpa accept\n/tpa deny");
									sender.sendMessage(ChatColor.GREEN + "tpa request successfully sent to " + args[0]);
									players.put(victem.getName().toLowerCase(), sender.getName().toLowerCase());
									final String usender = sender.getName();
									final String victemname = victem.getName();
									Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

										@Override
										public void run() {
											if(players.containsKey(victemname.toLowerCase())) {
												if(victem instanceof Player) {
													sender.sendMessage(ChatColor.RED + "tpa request from " + usender + " has been canceled");
												}
												if(sender instanceof Player) {
													sender.sendMessage(ChatColor.RED + "your tpa request has been overtime");
												}
												players.remove(victemname.toLowerCase());
											}
										}
										
									}, 1000);
								} else {
									sender.sendMessage(ChatColor.RED + args[0] + " has to much open requests please wait some time");
								}
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
