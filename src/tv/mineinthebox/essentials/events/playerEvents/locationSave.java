package tv.mineinthebox.essentials.events.playerEvents;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;

public class locationSave implements Listener {
	@EventHandler
	public void saveLocation(PlayerQuitEvent e) {
		Location loc = e.getPlayer().getLocation();
		xEssentialsMemory.returnPlayer(e.getPlayer()).put("location.x", loc.getX());
		xEssentialsMemory.returnPlayer(e.getPlayer()).put("location.y", loc.getY());
		xEssentialsMemory.returnPlayer(e.getPlayer()).put("location.z", loc.getZ());
		xEssentialsMemory.returnPlayer(e.getPlayer()).put("location.yaw", loc.getYaw());
		xEssentialsMemory.returnPlayer(e.getPlayer()).put("location.world", loc.getWorld().getName());
		xEssentialsMemory.updatePlayerConfig(e.getPlayer());
	}
}
