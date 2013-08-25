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
import tv.mineinthebox.resources.timeunit.timeunits;
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
		} else if(ban.isTempBanned(e.getPlayer())) {
			if(timeunits.isOverTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans"))) {
				fileManager.writeFile(e.getPlayer().getName() + ".yml", "Tempbanned", false, fileManager.getDir() + File.separator + "bans");
				fileManager.writeFile(e.getPlayer().getName() + ".yml", "time", 0, fileManager.getDir() + File.separator + "bans");
				return;
			} else {
				e.setJoinMessage("");
				e.getPlayer().kickPlayer("you where banned tempory by staff member " + fileManager.getStringValue(e.getPlayer().getName() + ".yml", "BannedBy", fileManager.getDir() + File.separator + "bans") + "\n till " + timeunits.getElapsedTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans")));
			}
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
				if(ban.isBanned(e.getPlayer()) || ban.isTempBanned(e.getPlayer())) {
					e.setJoinMessage("");	
					return;
				}
				ban.getAlternateAccounts(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void modreqEvent(PlayerJoinEvent e) {
		if(e.getPlayer().hasPermission("xEssentials.command.check.admin")) {
			if(fileManager.file_exists(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done")) {
				File f = fileManager.returnFile(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done");
				String date = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "date", fileManager.getDir() + File.separator + "modreq_done");
				String comment = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "comment", fileManager.getDir() + File.separator + "modreq_done");
				String helped = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "helped", fileManager.getDir() + File.separator + "modreq_done");
				e.getPlayer().sendMessage(ChatColor.GREEN + "[" + date + "]" + "your modreq has been closed by staff member " + helped);
				e.getPlayer().sendMessage(ChatColor.GRAY + "comment: " + comment);
				f.delete();
			}
			File[] list = fileManager.getFileList(fileManager.getDir() + File.separator + "modreq");
			int size = list.length;
			if(size > 0) {
				e.getPlayer().sendMessage(ChatColor.GOLD + "[modreq]" + ChatColor.RED + "there are modreqs open, " + size + " in total.");	
			} else {
				e.getPlayer().sendMessage(ChatColor.GOLD + "[modreq]" + ChatColor.GRAY + "currently there are no modreqs open!");	
			}
		} else {
			if(fileManager.file_exists(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done")) {
				File f = fileManager.returnFile(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done");
				String date = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "date", fileManager.getDir() + File.separator + "modreq_done");
				String comment = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "comment", fileManager.getDir() + File.separator + "modreq_done");
				String helped = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "helped", fileManager.getDir() + File.separator + "modreq_done");
				e.getPlayer().sendMessage(ChatColor.GREEN + "[" + date + "]" + "your modreq has been closed by staff member " + helped);
				e.getPlayer().sendMessage(ChatColor.GRAY + "comment: " + comment);
				f.delete();
			}
		}
	}
	
	@EventHandler
	public void WorldGuardJoinMessage(PlayerJoinEvent e) {
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
	}

}
