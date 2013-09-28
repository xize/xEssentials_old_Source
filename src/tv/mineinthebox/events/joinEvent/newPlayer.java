package tv.mineinthebox.events.joinEvent;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentialsMemory;

public class newPlayer implements Listener {
	
	@EventHandler
	public void onTeleportVanillaFix(PlayerJoinEvent e) {
		if(!e.getPlayer().hasPlayedBefore()) {
			Location loc = new Location(xEssentialsMemory.spawn_world, xEssentialsMemory.spawn_x, xEssentialsMemory.spawn_y, xEssentialsMemory.spawn_z);
			loc.setYaw(xEssentialsMemory.spawn_yaw);
			e.getPlayer().teleport(loc);
		}
	}

}
