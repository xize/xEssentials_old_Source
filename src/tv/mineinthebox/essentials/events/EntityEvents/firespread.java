package tv.mineinthebox.essentials.events.EntityEvents;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;

public class firespread implements Listener {
	
	@EventHandler
	public void firespreads(BlockSpreadEvent e) {
		if(e.getNewState().getType() == Material.FIRE) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void firespreadsdamage(BlockBurnEvent e) {
		if(e.getBlock().getState() != null) {
			e.setCancelled(true);
		}
	}

}
