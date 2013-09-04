package tv.mineinthebox.events.kickEvent;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.bansystem.ban;
import tv.mineinthebox.resources.timeunit.timeunits;

public class banKickEvent implements Listener {
	@EventHandler
	public void bannedPlayer(PlayerKickEvent e) {
		if(ban.isBanned(e.getPlayer())) {
			e.setLeaveMessage("");
		} else if(ban.isTempBanned(e.getPlayer())) {
			if(!timeunits.isOverTime(fileManager.getLongValue(e.getPlayer().getName() + ".yml", "time", fileManager.getDir() + File.separator + "bans"))) {
				e.setLeaveMessage("");
			}
		}
	}
}
