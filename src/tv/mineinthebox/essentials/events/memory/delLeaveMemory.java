package tv.mineinthebox.essentials.events.memory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;

public class delLeaveMemory implements Listener {
	@EventHandler
	public void delPlayerMemory(PlayerQuitEvent e) {
		//first save and then quit
		xEssentialsMemory.updatePlayerConfig(e.getPlayer());
		xEssentialsMemory.returnPlayer(e.getPlayer()).clear();
		xEssentialsMemory.removePlayer(e.getPlayer());
	}
}
