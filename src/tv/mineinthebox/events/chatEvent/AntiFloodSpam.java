package tv.mineinthebox.events.chatEvent;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.xEssentialsMemory;

@SuppressWarnings("deprecation")
public class AntiFloodSpam implements Listener {
	
	public static HashMap<String, level> messages = new HashMap<String, level>();
	
	public static enum level {
		normal,
		medium,
		flood
	};
	
	@EventHandler
	public void check_antiflood(final PlayerChatEvent e) {
		if(e.getMessage() != null) {
			if(messages.containsKey(e.getPlayer().getName())) {
				if(messages.get(e.getPlayer().getName()) == level.normal) {
					messages.put(e.getPlayer().getName(), level.medium);
					Bukkit.getScheduler().scheduleAsyncDelayedTask(xEssentials.getPlugin(), new Runnable() {
						@Override
						public void run() {
							if(messages.containsKey(e.getPlayer().getName())) {
								if(messages.get(e.getPlayer().getName()) == level.medium) {
									messages.remove(e.getPlayer().getName());
								}
							}
						}
						
					}, 3L);
				} else if(messages.get(e.getPlayer().getName()) == level.medium) {
					messages.put(e.getPlayer().getName(), level.flood);
					Bukkit.getScheduler().scheduleAsyncDelayedTask(xEssentials.getPlugin(), new Runnable() {
						@Override
						public void run() {
							if(messages.containsKey(e.getPlayer().getName())) {
								if(messages.get(e.getPlayer().getName()) == level.flood) {
									messages.remove(e.getPlayer().getName());
								}
							}
						}
						
					}, 3L);
				} else if(messages.get(e.getPlayer().getName()) == level.flood) {
					e.getPlayer().getServer().dispatchCommand(Bukkit.getConsoleSender(), "mute " + e.getPlayer().getName() + " 1D");
					e.getPlayer().kickPlayer(xEssentialsMemory.antiFloodSpamMessage);
				}
			} else {
				messages.put(e.getPlayer().getName(), level.normal);
				Bukkit.getScheduler().scheduleAsyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

					@Override
					public void run() {
						if(messages.containsKey(e.getPlayer().getName())) {
							if(messages.get(e.getPlayer().getName()) == level.normal) {
								messages.remove(e.getPlayer().getName());
							}
						}
					}
					
				}, 3L);
			}
		}
	}

}
