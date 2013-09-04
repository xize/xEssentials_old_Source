package tv.mineinthebox.events.joinEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.vanish.vanishApi;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi.flagType;

public class joinMessageEvent implements Listener {
	@EventHandler
	public void joinMessage(PlayerJoinEvent e) {
		if(ban.isBanned(e.getPlayer()) || ban.isTempBanned(e.getPlayer())) {
			e.setJoinMessage("");	
			return;
		}
		if(vanishApi.isVanished(e.getPlayer())) {
			e.setJoinMessage("");	
			return;
		}
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
			if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setJoinMessage(ChatColor.GRAY + "a safe staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					}
				}
				e.setJoinMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
				return;
			} else {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setJoinMessage(ChatColor.GRAY + "a safe " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					}
				}
				e.setJoinMessage(ChatColor.GRAY + "a wild " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
				return;
			}
		} else {
			e.setJoinMessage(ChatColor.GREEN + e.getPlayer().getName() + " has joined :)");
		}
		if(Bukkit.getPluginManager().isPluginEnabled("supertowns")) {
			supertowns supert = (supertowns) Bukkit.getPluginManager().getPlugin("supertowns");
			if(supert.getCoreApi().checkTown(e.getPlayer().getLocation().getChunk().getX(), e.getPlayer().getLocation().getChunk().getZ(), e.getPlayer().getWorld())) {
				String townName = supert.getCoreApi().getTownNameOnLocation(e.getPlayer().getLocation().getChunk().getX(), e.getPlayer().getLocation().getChunk().getZ(), e.getPlayer().getWorld());
				if(supert.getCoreApi().returnFlagTypes(townName, flagType.hostile_mob_spawn)) {
					if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
						e.setJoinMessage(ChatColor.GRAY + "a safe staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					} else {
						e.setJoinMessage(ChatColor.GRAY + "a safe " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					}
				} else {
					if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
						e.setJoinMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					} else {
						e.setJoinMessage(ChatColor.GRAY + "a wild " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					}
				}
			} else {
				if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
					e.setJoinMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					return;
				} else {
					e.setJoinMessage(ChatColor.GRAY + "a wild " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					return;
				}
			}
		}
	}

}
