package tv.mineinthebox.essentials.events.playerEvents;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentials;

public class afkScheduler implements Listener {
	
	public static HashMap<String, Location> autoAfk = new HashMap<String, Location>();
	
	public static void startAfkTimer() {
		Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(autoAfk.containsKey(p.getName())) {
						Location currentLoc = p.getLocation();
						if(autoAfk.get(p.getName()).equals(currentLoc)) {
							if(!afkCheck.afkList.contains(p.getName())) {
								Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has been afk");
								afkCheck.afkList.add(p.getName());
							}
						} else {
							autoAfk.put(p.getName(), p.getLocation());
						}
					} else {
						autoAfk.put(p.getName(), p.getLocation());
					}
				}
			}
			
		}, 0, 3000);
	}
	
	@EventHandler
	public void afkTimerQuit(PlayerQuitEvent e) {
		if(autoAfk.containsKey(e.getPlayer().getName())) {
			autoAfk.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void afkTimerQuit(PlayerKickEvent e) {
		if(autoAfk.containsKey(e.getPlayer().getName())) {
			autoAfk.remove(e.getPlayer().getName());
		}
	}

}
