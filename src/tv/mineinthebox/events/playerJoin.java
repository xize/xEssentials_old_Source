package tv.mineinthebox.events;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.vanish.vanishApi;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class playerJoin implements Listener {
	
	@EventHandler
	public void checkIfBanned(PlayerJoinEvent e) {
		if(ban.isBanned(e.getPlayer())) {
			e.getPlayer().kickPlayer(fileManager.getStringValue(e.getPlayer().getName() + ".yml", "Reason", fileManager.getDir() + File.separator + "bans"));
			e.setJoinMessage("");	
		}
	}
	
	@EventHandler
	public void HandleOnCommandTask(PlayerJoinEvent e) {
		if(fileManager.file_exists(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "tasks")) {
			e.getPlayer().getServer().dispatchCommand(Bukkit.getConsoleSender(), fileManager.getStringValue(e.getPlayer().getName() + ".yml", "command", fileManager.getDir() + File.separator + "tasks"));
		}
	}
	
	@EventHandler
	public void vanishHandle(PlayerJoinEvent e) {
		if(vanishApi.isVanished(e.getPlayer())) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.hidePlayer(e.getPlayer());
			}
			e.setJoinMessage("");
		} else {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(vanishApi.isVanished(p)) {
					e.getPlayer().hidePlayer(p);
				}
			}
		}
	}
	
	@EventHandler
	public void checkAlts(PlayerJoinEvent e) {
		if(fileManager.file_exists("ban.yml", fileManager.getDir())) {
			if(fileManager.getBooleanValue("ban.yml", "ban.system.showAlternateAccounts", fileManager.getDir())) {
				if(ban.isBanned(e.getPlayer())) {
					e.setJoinMessage("");	
					return;
				}
				ban.getAlternateAccounts(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void WorldGuardJoinMessage(PlayerJoinEvent e) {
		if(ban.isBanned(e.getPlayer())) {
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
	}

}
