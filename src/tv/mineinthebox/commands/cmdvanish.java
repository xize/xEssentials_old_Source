package tv.mineinthebox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import tv.mineinthebox.permissions.consolePermission;
import tv.mineinthebox.permissions.playerPermission;
import tv.mineinthebox.resources.vanish.vanishApi;

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
							if(!vanishApi.isVanished(p)) {
								if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
									WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
									for(ProtectedRegion region : wg.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
										if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
											Bukkit.broadcastMessage(ChatColor.RED + "Whoosh! staff member " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has left the game safely!");
											vanishApi.vanish(p);
											return false;
										}
									}
									Bukkit.broadcastMessage(ChatColor.RED + "Whoosh! staff member " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has left the game in wild!");
									vanishApi.vanish(p);
								} else {
									Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has left!");
									vanishApi.vanish(p);
								}
							} else {
								p.sendMessage(ChatColor.RED + "you are allready vanished so you can't fake quit, use /vanish fakejoin instead or /vanish");
							}
						} else if(args[0].equalsIgnoreCase("fj") || args[0].equalsIgnoreCase("fakejoin")) {
							if(vanishApi.isVanished(p)) {
								if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
									WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
									for(ProtectedRegion region : wg.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
										if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
											Bukkit.broadcastMessage(ChatColor.GRAY + "a safe staff member " + p.getName() + ChatColor.GRAY + " has been appeared!");
											vanishApi.vanish(p);
											return false;
										}
									}
									Bukkit.broadcastMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has been appeared!");
									vanishApi.unvanish(p);
								} else {
									Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has joined :)");
									vanishApi.unvanish(p);
								}
							} else {
								p.sendMessage(ChatColor.RED + "you are allready are unvanished so you can't fake join, use /vanish fakequit instead or /vanish");
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
