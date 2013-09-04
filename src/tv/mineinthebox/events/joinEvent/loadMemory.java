package tv.mineinthebox.events.joinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentialsMemory;

public class loadMemory implements Listener {

	@EventHandler
	public void loadPlayerMemory(PlayerJoinEvent e) {
		xEssentialsMemory.setPlayer(e.getPlayer());
	}
}
