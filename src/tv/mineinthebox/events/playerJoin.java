package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.bansystem.ban;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class playerJoin implements Listener {
	
	@EventHandler
	public void checkAlts(PlayerJoinEvent e) {
		if(fileManager.file_exists("ban.yml", fileManager.getDir())) {
			if(fileManager.getBooleanValue("ban.yml", "ban.system.showAlternateAccounts", fileManager.getDir())) {
				ban.getAlternateAccounts(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void WorldGuardJoinMessage(PlayerJoinEvent e) {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
			if(e.getPlayer().hasPermission("xEssentials.isStaff")) {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setJoinMessage(ChatColor.GRAY + "a safe staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					} else {
						e.setJoinMessage(ChatColor.GRAY + "a wild staff member " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					}
				}
			} else {
				for(ProtectedRegion region : wg.getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY) {
						e.setJoinMessage(ChatColor.GRAY + "a safe " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
						return;
					} else {
						e.setJoinMessage(ChatColor.GRAY + "a wild " + ChatColor.GREEN + e.getPlayer().getName() + ChatColor.GRAY + " has been appeared!");
					}
				}
			}
		} else {
			e.setJoinMessage(ChatColor.GREEN + e.getPlayer().getName() + " has joined :)");
		}
	}
	
	@EventHandler
	public void Xeph0reJoin(PlayerJoinEvent e) {
		if(e.getPlayer().getName().equalsIgnoreCase("Xeph0re")) {
			e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&c()==[:::::::::::::>&e Developer of xEssentials &c<:::::::::::::]==()\n&2»&7 Xeph0re joined!"));
			e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.WITHER_IDLE, 1, 100);
			e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
		}
	}

}
