package tv.mineinthebox.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLeave implements Listener {
	
	@EventHandler
	public void firefly(PlayerQuitEvent e) {
		if(playermove.firefly.contains(e.getPlayer().getName())) {
			playermove.firefly.remove(e.getPlayer().getName());
		}
	}

}
