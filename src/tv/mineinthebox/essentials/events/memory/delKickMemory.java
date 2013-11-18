package tv.mineinthebox.essentials.events.memory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;

public class delKickMemory implements Listener {
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void delPlayerMemory(PlayerKickEvent e) {
		xEssentialsMemory.updatePlayerConfig(e.getPlayer());
		xEssentialsMemory.returnPlayer(e.getPlayer()).clear();
		xEssentialsMemory.removePlayer(e.getPlayer());
	}

}
