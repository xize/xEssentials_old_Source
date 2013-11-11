package tv.mineinthebox.essentials.events.EntityEvent;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class endermanCheck implements Listener {

	@EventHandler
	public void disableGrief(EntityChangeBlockEvent e) {
		if(e.getEntity().getType() == EntityType.ENDERMAN) {
			e.setCancelled(true);
		}
	}

}
