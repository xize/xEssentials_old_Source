package tv.mineinthebox.events.leaveEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.vanish.vanishApi;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi.flagType;

public class leaveMessageEvent implements Listener {
	@EventHandler
	public void LeaveMessage(PlayerQuitEvent e) {
		if(ban.isBanned(e.getPlayer())) {
			e.setQuitMessage("");	
			return;
		}
		if(vanishApi.isVanished(e.getPlayer())) {
			e.setQuitMessage("");	
			return;
		}
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
			if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setQuitMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
						return;
					}
				}
				e.setQuitMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
				return;
			} else {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setQuitMessage("Whoosh! " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
						return;
					}
				}
				e.setQuitMessage(ChatColor.RED + "Whoosh! " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
				return;
			}
		} else {
			e.setQuitMessage(ChatColor.GREEN + e.getPlayer().getName() + " has left!");
		}
		if(Bukkit.getPluginManager().isPluginEnabled("supertowns")) {
			supertowns supert = (supertowns) Bukkit.getPluginManager().getPlugin("supertowns");
			if(supert.getCoreApi().checkTown(e.getPlayer().getLocation().getChunk().getX(), e.getPlayer().getLocation().getChunk().getZ(), e.getPlayer().getWorld())) {
				String townName = supert.getCoreApi().getTownNameOnLocation(e.getPlayer().getLocation().getChunk().getX(), e.getPlayer().getLocation().getChunk().getZ(), e.getPlayer().getWorld());
				if(supert.getCoreApi().returnFlagTypes(townName, flagType.hostile_mob_spawn)) {
					if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
						e.setQuitMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
						return;
					} else {
						e.setQuitMessage("Whoosh! " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game safely!");
						return;
					}
				} else {
					if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
						e.setQuitMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
						return;
					} else {
						e.setQuitMessage(ChatColor.RED + "Whoosh! " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
						return;
					}
				}
			} else {
				if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
					e.setQuitMessage(ChatColor.RED + "Whoosh!" + ChatColor.GRAY + " staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
					return;
				} else {
					e.setQuitMessage(ChatColor.RED + "Whoosh! " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has left the game in wild!");
					return;
				}
			}
		}
	}

}
