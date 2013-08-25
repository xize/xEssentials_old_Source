package tv.mineinthebox.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.permissions.playerPermission;

public class cmdteleport {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("teleport")) {
			if(sender.hasPermission("xEssentials.command.teleport")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[teleport help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <player> " + ChatColor.WHITE + ": teleports you to a player");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <player1> <player2> " + ChatColor.WHITE + ": teleports player1 to player2");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <offlinePlayer> " + ChatColor.WHITE + ": teleports you to a offline player");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <x y z> " + ChatColor.WHITE + ": teleports you to a coordinate in the current world");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <x y z world> " + ChatColor.WHITE + ": teleports you to a coordinate in a defined world");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[teleport help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <player> " + ChatColor.WHITE + ": teleports you to a player");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <player1> <player2> " + ChatColor.WHITE + ": teleports player1 to player2");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <offlinePlayer> " + ChatColor.WHITE + ": teleports you to a offline player");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <x y z> " + ChatColor.WHITE + ": teleports you to a coordinate in the current world");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/teleport <x y z world> " + ChatColor.WHITE + ": teleports you to a coordinate in a defined world");
					} else {
						if(sender instanceof Player) {
							Player p = (Player) sender;
							Player victem = Bukkit.getPlayer(args[0]);
							if(victem instanceof Player) {
								sender.sendMessage(ChatColor.GREEN + "teleporting to player " + args[0]);
								teleport(p, victem);
							} else {
								if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "players")) {
									Double x = fileManager.getDoubleValue(args[0] + ".yml", "location.x", fileManager.getDir() + File.separator + "players");
									Double y = fileManager.getDoubleValue(args[0] + ".yml", "location.y", fileManager.getDir() + File.separator + "players");
									Double z = fileManager.getDoubleValue(args[0] + ".yml", "location.z", fileManager.getDir() + File.separator + "players");
									int yaw = fileManager.getIntegerValue(args[0] + ".yml", "location.yaw", fileManager.getDir() + File.separator + "players");
									if(Bukkit.getWorld(fileManager.getStringValue(args[0] + ".yml", "location.world", fileManager.getDir() + File.separator + "players")) != null) {
										World w = Bukkit.getWorld(fileManager.getStringValue(args[0] + ".yml", "location.world", fileManager.getDir() + File.separator + "players"));
										Location loc = p.getLocation();
										loc.setX(x);
										loc.setY(y);
										loc.setZ(z);
										loc.setYaw(yaw);
										loc.setWorld(w);
										sender.sendMessage(ChatColor.GREEN + "teleporting to offline player " + args[0]);
										teleport(p, loc);
									} else {
										sender.sendMessage(ChatColor.RED + "it seems the location of this offline player is inside a unloaded world called: " + fileManager.getStringValue(args[0] + ".yml", "location.world", fileManager.getDir() + File.separator + "players"));
									}
								} else {
									sender.sendMessage(ChatColor.RED + args[0] + " has not played here before");
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "a console cannot teleport to players in game!");
						}
					}
				} else if(args.length == 2) {
					Player player1 = Bukkit.getPlayer(args[0]);
					Player player2 = Bukkit.getPlayer(args[1]);
					if(player1 instanceof Player) {
						if(player2 instanceof Player) {
							sender.sendMessage(ChatColor.GREEN + "successfully teleported " + player1.getName() + " to " + player2.getName());
							teleport(player1, player2);
						}
					}
				} else if(args.length == 3) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						Location loc = p.getLocation();
						try {
							Double x = Double.parseDouble(args[0]);
							Double y = Double.parseDouble(args[1]);
							Double z = Double.parseDouble(args[2]);
							loc.setX(x);
							loc.setY(y);
							loc.setZ(z);
							sender.sendMessage(ChatColor.GREEN + "teleporting to x:" + x + " y:" + y + " z:" + z + " world:" + loc.getWorld().getName());
							teleport(p, loc);
						} catch(NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + "you are only allowed to use numbers while using 3 arguments!, x y z");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "a console has no permissions to teleport to a location!");
					}
				} else if(args.length == 4) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						Location loc = p.getLocation();
						try {
							if(Bukkit.getWorld(args[3]) != null) {
								World w = Bukkit.getWorld(args[3]);
								Double x = Double.parseDouble(args[0]);
								Double y = Double.parseDouble(args[1]);
								Double z = Double.parseDouble(args[2]);
								loc.setX(x);
								loc.setY(y);
								loc.setZ(z);
								loc.setWorld(w);
								sender.sendMessage(ChatColor.GREEN + "teleporting to x:" + x + " y:" + y + " z:" + z + " world:" + loc.getWorld().getName());
								teleport(p, loc);
							} else {
								sender.sendMessage(ChatColor.RED + "error world " + args[3] + " is not loaded!");
							}
						} catch(NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + "you are only allowed to use numbers while using 3 arguments!, x y z Worldname");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "a console has no permissions to teleport to a location!");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

	public static void teleport(Player p, Player toPlayer) {
		if(p.isInsideVehicle()) {
			p.getVehicle().eject();
			p.teleport(toPlayer.getLocation());
		} else {
			p.teleport(toPlayer.getLocation());
		}
	}

	public static void teleport(Player p, Location loc) {
		if(p.isInsideVehicle()) {
			p.getVehicle().eject();
			loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
			p.teleport(loc);
		} else {
			p.teleport(loc);
		}
	}

}
