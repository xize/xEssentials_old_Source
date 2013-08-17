package tv.mineinthebox.events;

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

public class playerLeave implements Listener {
	
	@EventHandler
	public void firefly(PlayerQuitEvent e) {
		if(playermove.firefly.contains(e.getPlayer().getName())) {
			playermove.firefly.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void WorldGuardJoinMessage(PlayerQuitEvent e) {
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
	}

}
