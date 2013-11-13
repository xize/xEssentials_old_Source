package tv.mineinthebox.essentials.events.motd;

import java.util.ListIterator;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;

public class motd implements Listener {
	
	ListIterator<String> it = xEssentialsMemory.motdMessages.listIterator(); 
	
	@EventHandler
	public void setMothd(ServerListPingEvent e) {
		if(xEssentialsMemory.motdEnabled) {
			e.setMotd(ChatColor.translateAlternateColorCodes('&', xEssentialsMemory.motdMessage));	
		} else if(xEssentialsMemory.motdRandom) {
			if(it.hasNext()) {
				e.setMotd(ChatColor.translateAlternateColorCodes('&', it.next()));
			} else {
				while(it.hasPrevious()) {
					it.previous();
				}
				if(it.hasNext()) {
					e.setMotd(ChatColor.translateAlternateColorCodes('&', it.next()));
				}
			}
		}
	}
}
