package tv.mineinthebox.events.leaveEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentials;

public class delLeaveMemory implements Listener {
	@EventHandler
	public void delPlayerMemory(PlayerJoinEvent e) {
		xEssentials.mem.returnPlayer(e.getPlayer()).clear();
		xEssentials.mem.removePlayer(e.getPlayer());
	}
}
