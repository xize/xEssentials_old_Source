package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdhometp {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hometp")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				Location loc = p.getLocation();
				if(sender.hasPermission("xEssentials.command.hometp")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[hometp help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/hometp <player> " + ChatColor.WHITE + ": teleports to a players home");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/hometp <offlinePlayer> " + ChatColor.WHITE + ": teleports to a offline players home");
					} else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							sender.sendMessage(ChatColor.GOLD + ".oO___[hometp help]___Oo.");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/hometp <player> " + ChatColor.WHITE + ": teleports to a players home");
							sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/hometp <offlinePlayer> " + ChatColor.WHITE + ": teleports to a offline players home");
						} else {
							Player victem = Bukkit.getPlayer(args[0]);
							if(victem instanceof Player) {
								if(fileManager.file_exists(victem.getName() + ".yml", fileManager.getDir() + File.separator + "homes")) {
									Double x = fileManager.getDoubleValue(victem.getName() + ".yml", "x", fileManager.getDir() + File.separator + "homes");
									Double y = fileManager.getDoubleValue(victem.getName() + ".yml", "y", fileManager.getDir() + File.separator + "homes");
									Double z = fileManager.getDoubleValue(victem.getName() + ".yml", "z", fileManager.getDir() + File.separator + "homes");
									int yaw = fileManager.getIntegerValue(victem.getName() + ".yml", "yaw", fileManager.getDir() + File.separator + "homes");
									String w = fileManager.getStringValue(victem.getName() + ".yml", "world", fileManager.getDir() + File.separator + "homes");
									if(Bukkit.getWorld(w) != null) {
										loc.setX(x);
										loc.setY(y);
										loc.setZ(z);
										loc.setYaw(yaw);
										loc.setWorld(Bukkit.getWorld(w));
										sender.sendMessage(ChatColor.GREEN + "teleporting to " + victem.getName() + " his home location");
										cmdteleport.teleport(p, loc);
									} else {
										sender.sendMessage(ChatColor.RED + victem.getName() + " home is located in a non loaded world: " + w);
									}
								} else {
									sender.sendMessage(ChatColor.RED + victem.getName() + " hasn't set a home");
								}
							} else {
								if(fileManager.file_exists(args[0] + ".yml", fileManager.getDir() + File.separator + "homes")) {
									Double x = fileManager.getDoubleValue(args[0] + ".yml", "x", fileManager.getDir() + File.separator + "homes");
									Double y = fileManager.getDoubleValue(args[0] + ".yml", "y", fileManager.getDir() + File.separator + "homes");
									Double z = fileManager.getDoubleValue(args[0] + ".yml", "z", fileManager.getDir() + File.separator + "homes");
									int yaw = fileManager.getIntegerValue(args[0] + ".yml", "yaw", fileManager.getDir() + File.separator + "homes");
									String w = fileManager.getStringValue(args[0] + ".yml", "world", fileManager.getDir() + File.separator + "homes");
									if(Bukkit.getWorld(w) != null) {
										loc.setX(x);
										loc.setY(y);
										loc.setZ(z);
										loc.setYaw(yaw);
										loc.setWorld(Bukkit.getWorld(w));
										sender.sendMessage(ChatColor.GREEN + "teleporting to " + args[0] + " his home location");
										cmdteleport.teleport(p, loc);
									} else {
										sender.sendMessage(ChatColor.RED + args[0] + " home is located in a non loaded world: " + w);
									}
								} else {
									sender.sendMessage(ChatColor.RED + args[0] + " hasn't set a home");
								}
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
