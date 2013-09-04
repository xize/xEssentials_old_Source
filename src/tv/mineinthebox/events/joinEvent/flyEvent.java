package tv.mineinthebox.events.joinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.xEssentials;

public class flyEvent implements Listener {
	@EventHandler
	public void onFly(PlayerJoinEvent e) {
		if(xEssentials.mem.returnPlayer(e.getPlayer()).containsKey("fly")) {
			Boolean bol = (Boolean) xEssentials.mem.returnPlayer(e.getPlayer()).get("fly");
			if(bol instanceof Boolean) {
				if(bol) {
					e.getPlayer().setAllowFlight(true);
					e.getPlayer().setFlying(true);
				}
			}
		}
	}
}
