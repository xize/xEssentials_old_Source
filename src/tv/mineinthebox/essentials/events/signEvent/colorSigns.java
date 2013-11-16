package tv.mineinthebox.essentials.events.signEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class colorSigns implements Listener {
	
	@EventHandler
	public void setSignColors(SignChangeEvent e) {
		if(e.getPlayer().hasPermission("xEssentials.signs.color")) {
			e.setLine(0, ChatColor.translateAlternateColorCodes('&', e.getLine(0)));
			e.setLine(1, ChatColor.translateAlternateColorCodes('&', e.getLine(1)));
			e.setLine(2, ChatColor.translateAlternateColorCodes('&', e.getLine(2)));
			e.setLine(3, ChatColor.translateAlternateColorCodes('&', e.getLine(3)));
		}
	}

}
