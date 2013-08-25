package tv.mineinthebox.events;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.timeunit.timeunits;

public class playerKickEvent implements Listener {
	
	@EventHandler
	public void fireFly(PlayerKickEvent e) {
		if(playermove.firefly.contains(e.getPlayer().getName())) {
			playermove.firefly.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void bannedPlayer(PlayerKickEvent e) {
		if(ban.isBanned(e.getPlayer())) {
			e.setLeaveMessage("");
			//e.setReason(e.getPlayer().getName() + " is banned");
		} else if(ban.isTempBanned(e.getPlayer())) {
			if(!timeunits.isOverTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans"))) {
				e.setLeaveMessage("");
				//e.setReason(e.getPlayer().getName() + " is tempory banned till " + timeunits.getElapsedTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans")));	
			}
		}
	}
	
	@EventHandler
	public void saveLocation(PlayerKickEvent e) {
		Location loc = e.getPlayer().getLocation();
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "location.x", loc.getX(), fileManager.getDir() + File.separator + "players");
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "location.y", loc.getY(), fileManager.getDir() + File.separator + "players");
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "location.z", loc.getZ(), fileManager.getDir() + File.separator + "players");
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "location.yaw", loc.getYaw(), fileManager.getDir() + File.separator + "players");
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "location.world", loc.getWorld().getName(), fileManager.getDir() + File.separator + "players");
	}

}
