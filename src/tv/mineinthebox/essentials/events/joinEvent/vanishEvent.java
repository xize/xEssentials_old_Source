package tv.mineinthebox.essentials.events.joinEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class vanishEvent implements Listener {
	@EventHandler
	public void vanishHandle(PlayerJoinEvent e) {
		if(vanishApi.isVanished(e.getPlayer())) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.hidePlayer(e.getPlayer());
			}
			e.setJoinMessage("");
		} else {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(vanishApi.isVanished(p)) {
					e.getPlayer().hidePlayer(p);
				}
			}
		}
	}
}
