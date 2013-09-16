package tv.mineinthebox.events.EntityEvent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class disableSpawnEggs implements Listener {

	@EventHandler
	public void throwEgg(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getPlayer().getItemInHand().getType() == Material.MONSTER_EGG || e.getPlayer().getItemInHand().getType() == Material.MONSTER_EGGS) {
				e.setCancelled(true);
			}
		}
	}
}
