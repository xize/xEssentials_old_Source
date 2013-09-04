package tv.mineinthebox.events.joinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.resources.bansystem.ban;

public class alternateAccountEvent implements Listener {

	@EventHandler
	public void checkAlts(PlayerJoinEvent e) {
		if(xEssentialsMemory.showAlternateAccounts) {
			if(ban.isBanned(e.getPlayer()) || ban.isTempBanned(e.getPlayer())) {
				e.setJoinMessage("");	
				return;
			}
			ban.getAlternateAccounts(e.getPlayer());
		}
	}

}
