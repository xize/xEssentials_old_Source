package tv.mineinthebox.essentials.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdtpid {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(sender.hasPermission("xEssentials.command.tpid")) {
			if(cmd.getName().equalsIgnoreCase("tp-id")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + "use /tp-id playername");
				} else if(args.length == 1) {
					if(fileManager.file_exists(args[0].toLowerCase()+".yml", fileManager.getDir() + File.separator + "modreq")) {
						try {
							Double x = fileManager.getDoubleValue(args[0].toLowerCase()+".yml", "x", fileManager.getDir() + File.separator + "modreq");
							Double y = fileManager.getDoubleValue(args[0].toLowerCase()+".yml", "y", fileManager.getDir() + File.separator + "modreq");
							Double z = fileManager.getDoubleValue(args[0].toLowerCase()+".yml", "z", fileManager.getDir() + File.separator + "modreq");
							int yaw = fileManager.getIntegerValue(args[0].toLowerCase()+".yml", "yaw", fileManager.getDir() + File.separator + "modreq");
							String world = fileManager.getStringValue(args[0].toLowerCase() + ".yml", "world", fileManager.getDir() + File.separator + "modreq");
							World w = Bukkit.getWorld(world);
							if(w instanceof World) {
								Location loc = new Location(w, x, y, z, yaw, 0);
								Player p = (Player) sender;
								if(p instanceof Player) {
									loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
									p.sendMessage(ChatColor.GREEN + "teleporting to modreq location of id:"+args[0]);
									p.teleport(loc);
								} else {
									sender.sendMessage(ChatColor.RED + "you are not a player cannot run this command");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "world does not exist");
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "player has no modreqs open!");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
