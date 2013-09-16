package tv.mineinthebox.events.EntityEvent;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import tv.mineinthebox.xEssentialsMemory;

public class zombieTarget implements Listener {
	
	@EventHandler
	public void getZombieTarget(EntityTargetLivingEntityEvent e) {
		if(e.getEntity() instanceof Zombie) {
			if(e.getTarget() instanceof Player) {
				if(e.getEntity().getLocation().distance(e.getTarget().getLocation()) > xEssentialsMemory.zombieRange) {
					e.setCancelled(true);
				}
			}
		}
	}

}
