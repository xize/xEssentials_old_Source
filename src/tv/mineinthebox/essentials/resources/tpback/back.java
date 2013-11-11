package tv.mineinthebox.essentials.resources.tpback;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.xEssentials;

public class back {
	
	public static HashMap<String, Location> locations = new HashMap<String, Location>();
	
	public static void setBack(Player p) {
		locations.put(p.getName(), p.getLocation());
		final String playerName = p.getName();
		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				if(locations.containsKey(playerName)) {
					locations.remove(playerName);
				}
			}
			
		}, 1000);
	}
	
	public static void setBack(Location loc, String playername) {
		locations.put(playername, loc);
		final String playerName = playername;
		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				if(locations.containsKey(playerName)) {
					locations.remove(playerName);
				}
			}
			
		}, 1000);
	}
	
	public static void removeBack(Player p) {
		if(locations.containsKey(p.getName())) {
			locations.remove(p.getName());
		}
	}

}
