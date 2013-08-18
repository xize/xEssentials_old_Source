package tv.mineinthebox.events;

import java.io.File;

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
			e.setReason(e.getPlayer().getName() + " is banned");
		} else if(ban.isTempBanned(e.getPlayer())) {
			if(!timeunits.isOverTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans"))) {
				e.setLeaveMessage("");
				e.setReason(e.getPlayer().getName() + " is tempory banned till " + timeunits.getElapsedTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans")));	
			}
		}
	}

}
