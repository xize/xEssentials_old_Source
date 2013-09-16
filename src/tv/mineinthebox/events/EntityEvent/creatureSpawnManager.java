package tv.mineinthebox.events.EntityEvent;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import tv.mineinthebox.xEssentialsMemory;

public class creatureSpawnManager implements Listener {
	
	@EventHandler
	public void checkMob(CreatureSpawnEvent e) {
		for(EntityType entity : EntityType.values()) {
			if(xEssentialsMemory.mobValues.containsKey(entity.name())) {
				if(!xEssentialsMemory.mobValues.get(entity.name())) {
					e.setCancelled(true);
				}
			}
		}
	}

}
