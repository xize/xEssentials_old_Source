package tv.mineinthebox.essentials.events.EntityEvents;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class enderDragonCheck implements Listener {
	
	@EventHandler
	public void enderdragon(EntityChangeBlockEvent e) {
		if(e.getEntity().getType() == EntityType.ENDER_DRAGON) {
			e.setCancelled(true);
		}
	}

}
