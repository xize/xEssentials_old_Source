package tv.mineinthebox.events.kickEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentials;

public class delKickMemory implements Listener {
	@EventHandler
	public void delPlayerMemory(PlayerJoinEvent e) {
		xEssentials.mem.removePlayer(e.getPlayer());
	}

}
