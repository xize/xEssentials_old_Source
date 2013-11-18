package tv.mineinthebox.essentials.events.vanishEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class vanishEntity implements Listener {
	
	@EventHandler
	public void ignoremob(EntityTargetLivingEntityEvent e) {
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			if(vanishApi.isVanished(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void stopinteract(EntityTargetEvent e) {
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			if(vanishApi.isVanished(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if(vanishApi.isVanished(p)) {
			if(vanishApi.vanishNoPickUp(p)) {
				e.setCancelled(true);
			} else {
				
			}
		} else {
			//do nothing
		}
	}

}
