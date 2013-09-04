package tv.mineinthebox.events.kickEvent;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.xEssentials;

public class saveLocationEvent implements Listener {
	@EventHandler
	public void saveLocation(PlayerQuitEvent e) {
		Location loc = e.getPlayer().getLocation();
		xEssentials.mem.returnPlayer(e.getPlayer()).put("location.x", loc.getX());
		xEssentials.mem.returnPlayer(e.getPlayer()).put("location.y", loc.getY());
		xEssentials.mem.returnPlayer(e.getPlayer()).put("location.z", loc.getZ());
		xEssentials.mem.returnPlayer(e.getPlayer()).put("location.yaw", loc.getYaw());
		xEssentials.mem.returnPlayer(e.getPlayer()).put("location.world", loc.getWorld().getName());
		xEssentials.mem.updatePlayerConfig(e.getPlayer());
	}
}
