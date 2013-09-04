package tv.mineinthebox.events.playerMoveEvent;

import org.bukkit.Effect;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.resources.vanish.vanishApi;

public class firefly implements Listener {
	@EventHandler
	public void fireFly(PlayerMoveEvent e) {
		if(xEssentialsMemory.returnPlayer(e.getPlayer()).containsKey("firefly")) {
			if((Boolean) xEssentialsMemory.returnPlayer(e.getPlayer()).get("firefly")) {
				if(vanishApi.isVanished(e.getPlayer())) {
					//only draw client side particles while being vanished
					e.getPlayer().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
					e.getPlayer().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.ENDER_SIGNAL, 1);
				} else {
					//draw particles towards everyone
					e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
					e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.ENDER_SIGNAL, 1);	
				}
			}
		}
	}
}
