package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class worldguard {
	
	public static void turnOffWand(Player player) {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) {
			WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
			if(player.hasPermission("worldedit.wand")) {	
				if(player.getGameMode() == GameMode.SURVIVAL) {
					if(we.getSession(player).isToolControlEnabled()) {
						we.getSession(player).setToolControl(false);
						player.sendMessage(ChatColor.GOLD + ".oO___[Gamemode alert]___Oo.");
						player.sendMessage(ChatColor.GRAY + "your worldedit wand has been " + ChatColor.GREEN + "disabled!");
						player.sendMessage(ChatColor.GRAY + "if you want to renable it switch to creative or use /toggleeditwand");
					}
				} else if(player.getGameMode() == GameMode.CREATIVE) {
					if(!we.getSession(player).isToolControlEnabled()) {
						we.getSession(player).setToolControl(true);
						player.sendMessage(ChatColor.GOLD + ".oO___[Gamemode alert]___Oo.");
						player.sendMessage(ChatColor.GRAY + "your worldedit wand has been " + ChatColor.GREEN + "Enabled!");
						player.sendMessage(ChatColor.GRAY + "if you want to redisable it switch to survival or use /toggleeditwand");
					}
				}
			}
		}
	}
	
	public static void sendVanishQuitMessage(Player p) {
		if(!vanishApi.isVanished(p)) {
			if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
				WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
				for(ProtectedRegion region : wg.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						Bukkit.broadcastMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has left the game safely!");
						vanishApi.vanish(p);
						return false;
					}
				}
				Bukkit.broadcastMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has left the game in wild!");
				vanishApi.vanish(p);
			}
		} else {
			p.sendMessage(ChatColor.RED + "you are allready vanished so you can't fake quit, use /vanish fakejoin instead or /vanish");
		}
	}
	
	public static void sendVanishJoinMessage(Player p) {
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
			}
		} else {
			p.sendMessage(ChatColor.RED + "you are allready are unvanished so you can't fake join, use /vanish fakequit instead or /vanish");
		}
	}
	
	public static void sendJoinMessage(Player p ) {
		WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		if(p.getPlayer().hasPermission("xEssentials.isStaff")) {
			for(ProtectedRegion region : wg.getRegionManager(p.getPlayer().getWorld()).getApplicableRegions(p.getPlayer().getLocation())) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
					Bukkit.broadcastMessage(ChatColor.GRAY + "a safe staff member " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					return;
				}
			}
			Bukkit.broadcastMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
			return;
		} else {
			for(ProtectedRegion region : wg.getRegionManager(p.getPlayer().getWorld()).getApplicableRegions(p.getPlayer().getLocation())) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
					Bukkit.broadcastMessage(ChatColor.GRAY + "a safe " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					return;
				}
			}
			Bukkit.broadcastMessage(ChatColor.GRAY + "a wild " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
			return;
		}
	}
	
	public static void sendQuitMessage(Player p) {
		WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		if(p.getPlayer().hasPermission("xEssentials.isStaff")) {
			for(ProtectedRegion region : wg.getRegionManager(p.getPlayer().getWorld()).getApplicableRegions(p.getPlayer().getLocation())) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
					Bukkit.broadcastMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
					return;
				}
			}
			Bukkit.broadcastMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
			return;
		} else {
			for(ProtectedRegion region : wg.getRegionManager(p.getPlayer().getWorld()).getApplicableRegions(p.getPlayer().getLocation())) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
					Bukkit.broadcastMessage(ChatColor.RED + "Whoosh! " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
					return;
				}
			}
			Bukkit.broadcastMessage(ChatColor.RED + "Whoosh! " + ChatColor.GREEN + p.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
			return;
		}
	}
	
	public static boolean isInRegion(Location loc) {
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
			for(ProtectedRegion region : wg.getRegionManager(loc.getWorld()).getApplicableRegions(loc)) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY || region.getFlag(DefaultFlag.PVP) == State.DENY) {
					//player has entered
					return true;
				}
			}
			return false;
	}
	
	public static void sendRegionMessage(Player p, Chunk from, Chunk to) {
		if(from.getX() != to.getX() || from.getZ() != to.getZ()) {
			if(!isInRegion(from.getBlock(1, 1, 1).getLocation()) && isInRegion(to.getBlock(1, 1, 1).getLocation())) {
				p.getPlayer().sendMessage(ChatColor.GOLD + ".oO___[Entering safe zone]___Oo.");
			} else if(isInRegion(from.getBlock(1, 1, 1).getLocation()) && !isInRegion(to.getBlock(1, 1, 1).getLocation())) {
				p.getPlayer().sendMessage(ChatColor.GOLD + ".oO___[Leaving safe zone]___Oo.");
			}
		}
	}

}
