package tv.mineinthebox.events.EntityEvent;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class disableFireWork implements Listener {
	
	@EventHandler
	public void fireworkDisable(ProjectileLaunchEvent e) {
		if(e.getEntity() instanceof Firework) {
			e.setCancelled(true);
		}
	}

}
