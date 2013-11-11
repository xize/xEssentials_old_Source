package tv.mineinthebox.essentials.events.joinEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.essentials.resources.ghostfactory.ghost;

public class ghostJoin implements Listener {
	
	@EventHandler
	public void setGhosts(PlayerJoinEvent e) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(ghost.isGhost(p.getName())) {
				ghost.addPlayerToTeam(e.getPlayer());
				return;
			}
		}
	}

}
