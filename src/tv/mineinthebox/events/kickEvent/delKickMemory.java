package tv.mineinthebox.events.kickEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentialsMemory;

public class delKickMemory implements Listener {
	@EventHandler
	public void delPlayerMemory(PlayerJoinEvent e) {
		xEssentialsMemory.removePlayer(e.getPlayer());
	}

}
