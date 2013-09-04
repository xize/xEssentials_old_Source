package tv.mineinthebox.events.joinEvent;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.timeunit.timeunits;

public class bancheck implements Listener {
	
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

}
