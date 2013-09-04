package tv.mineinthebox.events.joinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentials;

public class loadMemory implements Listener {

	@EventHandler
	public void loadPlayerMemory(final PlayerJoinEvent e) {
		xEssentials.mem.setPlayer(e.getPlayer());
	}
}
