package tv.mineinthebox.essentials.events.motd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class motd implements Listener {
	
	@EventHandler
	public void setMothd(ServerListPingEvent e) {
		//String s = fileManager.getStringValue("motd.yml", "message", fileManager.getDir());
		e.setMotd(ChatColor.translateAlternateColorCodes('&', "&ewe need help debugging ManCo supplycrates!"));
	}
}
