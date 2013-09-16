package tv.mineinthebox.events.playerDeathEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import tv.mineinthebox.resources.tpback.back;

public class deathBackEvent implements Listener {
	
	@EventHandler
	public void getDeathLocation(PlayerDeathEvent e) {
		back.setBack(e.getEntity());
	}

}
